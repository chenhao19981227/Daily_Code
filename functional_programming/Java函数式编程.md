# Java函数式编程

## **1.** **概述**

### **1.1** **为什么学？**

- 能够看懂公司里的代码
- 大数量下处理集合效率高，并且能自动使用多线程进行并行化处理
- 代码可读性高
- 消灭嵌套地狱

~~~java
//查询未成年作家的评分在70以上的书籍 由于洋流影响所以作家和书籍可能出现重复，需要进行去重
List<Book> bookList = new ArrayList<>();
Set<Book> uniqueBookValues = new HashSet<>();
Set<Author> uniqueAuthorValues = new HashSet<>();
for (Author author : authors) {
    if (uniqueAuthorValues.add(author)) {
        if (author.getAge() < 18) {
            List<Book> books = author.getBooks();
            for (Book book : books) {
                if (book.getScore() > 70) {
                    if (uniqueBookValues.add(book)) {
                        bookList.add(book);
                    }
                }
            }
        }
    }
}
System.out.println(bookList);
~~~

~~~java
List<Book> collect = authors.stream()
    .distinct()
    .filter(author -> author.getAge() < 18)
    .map(author -> author.getBooks())
    .flatMap(Collection::stream)
    .filter(book -> book.getScore() > 70)
    .distinct()
    .collect(Collectors.toList());
System.out.println(collect);
~~~



### **1.2** **函数式编程思想**

#### **1.2.1** **概念**

面向对象思想需要关注用什么对象完成什么事情。而函数式编程思想就类似于我们数学中的函数。它主要关注的是对数据进行了什么操作。

#### **1.2.2** **优点**

- 代码简洁，开发快速
- 接近自然语言，易于理解
- 易于"并发编程"

## **2. Lambda表达式**

### **2.1** **概述**

Lambda是JDK8中一个语法糖。他可以对某些匿名内部类的写法进行简化。它是函数式编程思想的一个重要体现。让我们不用关注是什么对象。而是更关注我们对数据进行了什么操作。

### **2.2** **核心原则**

可推导、可省略

比如：如果参数类型可以推导出来，可以省略参数类型；如果方法名可以推导出来，可以省略方法名

### **2. 3** **基本格式**

~~~txt
(参数列表)->{代码}
~~~

**示例**

~~~java
public class Example {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程，启动！");
            }
        }).start();
        new Thread(()-> System.out.println("线程，启动！")).start();
    }
}
~~~

**练习1**

```java
public class Practice1 {
    public static int calculateNum(IntBinaryOperator operator){
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }
    public static void main(String[] args) {
        // 匿名内部类写法
        System.out.println(calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        }));
        // Lambda写法
        System.out.println(calculateNum(((left, right) -> left+right)));
    }
}
```

**练习2**

```java
public class Practice2 {
    // 打印偶数
    public static void printEvenNum(IntPredicate predicate){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            if(predicate.test(i)){
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args) {
        // 匿名内部类实现
        printEvenNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value % 2 == 0;
            }
        });
        System.out.println("------------------");
        // 手动lambda实现
        printEvenNum(value -> value%2==0);
        System.out.println("------------------");
        // alt+enter自动实现
        printEvenNum(value -> value % 2 == 0);
    }
}
```

**练习3**

~~~java
public class Practice3 {
    public static <R> R typeConver(Function<String,R> function){
        String str = "1235";
        R result = function.apply(str);
        return result;
    }

    public static void main(String[] args) {
        // 匿名内部类实现
        System.out.println(typeConver(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        }));
        // lambda实现
        System.out.println(typeConver(s -> Integer.valueOf(s)).intValue());
    }
}
~~~

**练习4**

~~~java
public class Practice4 {
    public static void foreachArr(IntConsumer consumer){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            consumer.accept(i);
        }
    }

    public static void main(String[] args) {
        // 匿名内部类实现
        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });
        System.out.println("----------");
        // lambda表达式实现
        foreachArr(i-> System.out.println(i));
    }
}
~~~

### **2.4** **省略规则**

- 参数类型可以省略
- 方法体只有一句代码时大括号return和唯一一句代码的分号可以省略
- 方法只有一个参数时小括号可以省略
- 以上这些规则都记不住也可以省略不记，直接用alt+enter先转为匿名内部类，再转回lambda

## **3. Stream流**

### **3.1** **概述**

Java8的Stream使用的是函数式编程模式，如同它的名字一样，它可以被用来对集合或数组进行链状流式的操作。可以更方便的让我们对集合或数组操作。

### **3.2** **案例数据准备**

~~~java
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode//重写equals和hashcode，用于后期的去重使用
public class Author {
    //id
    private Long id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //简介
    private String intro;
    //作品
    private List<Book> books;
}
~~~

~~~java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode//用于后期的去重使用
public class Book {
    //id
    private Long id;
    //书名
    private String name;
    //分类
    private String category;
    //评分
    private Integer score;
    //简介
    private String intro;
}
~~~

~~~java
public class StreamDemo {
    private static List<Author> getAuthors() {
//数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();
        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的时代理解"));
        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);
        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }
}
~~~

### **3.3** **快速入门**

#### **3.3.1** **需求**

我们可以调用getAuthors方法获取到作家的集合。现在需要打印所有年龄小于18的作家的名字，并且要注意去重。

#### **3.3.2** **实现**

```java
public static void main(String[] args) {
    // 打印所有年龄小于18的作家，同时注意去重
    List<Author> authors = getAuthors();
    authors.stream()
            .distinct()
            .filter(author -> author.getAge()<18)
            .forEach(author -> System.out.println(author.getName()));
}
```

### **3.4** **常用操作**

#### **3.4.1** **创建流**

单列集合：`集合对象.stream()`

~~~java
List<Author> authors = getAuthors();
Stream<Author> stream = authors.stream();
~~~

数组： `Arrays.stream(数组)` 或者使用 `Stream.of `来创建

~~~java
Integer[] arr = {1,2,3,4,5};
Stream<Integer> stream = Arrays.stream(arr);
Stream<Integer> stream2 = Stream.of(arr);
~~~

双列集合：转换成单列集合后再创建

~~~java
Map<String,Integer> map = new HashMap<>();
map.put("蜡笔小新",19);
map.put("黑子",17);
map.put("日向翔阳",16);
Stream<Map.Entry<String, Integer>> stream = map.entrySet().stream();
~~~

#### **3.4.2** **中间操作**

##### **① filter**

可以对流中的元素进行条件过滤，符合过滤条件的才能继续留在流中。

例如：

~~~java
public class Filter {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 打印所有姓名长度大于1的作家的姓名
        authors.stream()
                .distinct()
                .filter(author -> author.getName().length()>1)
                .forEach(author -> System.out.println(author.getName()));
    }
}
~~~

##### **② map**

可以把对流中的元素进行转换或计算。

例如：

~~~java
public class Map {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 获取所有作家的姓名，并打印所有作家的姓名（转换）
        authors.stream()
                .map(author -> author.getName())
                .forEach(name-> System.out.println(name));

        // 获取所有作家的年龄，并增加10岁（转换+计算）
        authors.stream()
                .map(author -> author.getAge())
                .map(age->age+10)
                .forEach(age-> System.out.println(age));
    }
}
~~~

##### **③ distinct**

可以去除流中的重复元素。

例如：

~~~java
public class Distinct {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
    }
}
~~~

##### **④ sorted**

可以对流中的元素进行排序。

例如：

~~~java
public class Sorted {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 方法1：使用无参的sorted方法，这种方法需要Author实现Comparable,并重写其中的方法
        authors.stream()
                .distinct()
                .sorted()
                .forEach(author -> System.out.println(author.getAge()));
        // 方法2：使用带参数sorted方法，在sorted方法中重写排序逻辑
        authors.stream()
                .distinct() .sorted((o1, o2) -> o2.getAge()-o1.getAge())
                .forEach(author -> System.out.println(author.getAge()));
    }
}
~~~

##### **⑤ limit**

可以设置流的最大长度，超出的部分将被抛弃。

例如：

~~~java
public class Limit {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素,然后打印其中年龄最大的两个作家的姓名
        authors.stream()
                .distinct()
                .sorted(((o1, o2) -> o2.getAge()-o1.getAge()))
                .limit(2)
                .forEach(author -> System.out.println(author.getName()));
    }
}
~~~

##### **⑥ skip**

跳过流中的前n个元素，返回剩下的元素

例如：

~~~java
public class Skip {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序
        authors.stream()
                .distinct()
                .sorted(((o1, o2) -> o2.getAge()-o1.getAge()))
                .skip(1)
                .forEach(author -> System.out.println(author.getName()));
    }
}
~~~

##### **⑦ flatMap**

map只能把一个对象转换成另一个对象来作为流中的元素。而flatMap可以把一个对象转换成多个对象作为流中的元素

例如：

~~~java
public class FlatMap {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 打印所有书籍的名字。要求对重复的元素进行去重
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
        // 打印现有数据的所有分类。要求对分类进行去重。不能出现这种格式：哲学,爱情
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(category-> System.out.println(category));
    }
}
~~~

#### **3.4.3** **终结操作**

##### **① forEach**

对流中的元素进行遍历操作，我们通过传入的参数去指定对遍历到的元素进行什么具体操作。

例如：

~~~java
public class ForEach {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 输出所有作家的名字
        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
    }
}
~~~

##### **② count**

可以用来获取当前流中元素的个数。

例如：

~~~java
public class Count {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 打印这些作家的所出书籍的数目，注意删除重复元素
        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);
    }
}
~~~

##### **③ max&min**

可以用来或者流中的最值。

例如：

~~~java
public class MinAndMax {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 分别获取这些作家的所出书籍的最高分和最低分并打印
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((score1, score2) -> score1 - score2);
        System.out.println(max.get());
        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((score1, score2) -> score1 - score2);
        System.out.println(min.get());
    }
}
~~~

##### **④ collect**

把当前流转换成一个集合。

例如：

~~~java
public class Collect {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 获取一个存放所有作者名字的List集合
        List<String> nameList = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(nameList);

        // 获取一个所有书名的Set集合
        Set<String> bookNameSet = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getName())
                .collect(Collectors.toSet());
        System.out.println(bookNameSet);

        // 获取一个Map集合，map的key为作者名，value为book的List
        Map<String, List<Book>> map = authors.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
        for (Map.Entry<String, List<Book>> stringListEntry : map.entrySet()) {
            System.out.println(stringListEntry);
        }
    }
}
~~~

##### **⑤ anyMatch**

可以用来判断是否有任意符合匹配条件的元素，结果为boolean类型。

例如：

~~~java
public class AnyMatch {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 判断是否有年龄在29以上的作家
        boolean flag = authors.stream()
                .allMatch(author -> author.getAge() > 29);
        System.out.println(flag);
    }
}
~~~

##### **⑥ allMatch**

可以用来判断是否都符合匹配条件，结果为boolean类型。如果都符合结果为true，否则结果为false。

例如：

~~~java
public class AllMatch {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 判断是否所有的作家都是成年人
        boolean flag = authors.stream()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println(flag);
    }
}
~~~

##### **⑦ noneMatch**

可以判断流中的元素是否都不符合匹配条件。如果都不符合结果为true，否则结果为false

例如：

~~~java
public class NoneMatch {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 判断作家是否都没有超过100岁的
        boolean flag = authors.stream()
                .noneMatch(author -> author.getAge() > 100);
        System.out.println(flag);
    }
}
~~~

##### **⑧ findAny**

获取流中的任意一个元素。该方法没有办法保证获取的一定是流中的第一个元素。

例如：

~~~java
public class FindAny {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 获取任意一个年龄大于18的作家，如果存在就输出他的名字
        Optional<Author> writer = authors.stream()
                .filter(author -> author.getAge() > 18)
                .findAny();
        writer.ifPresent(author -> System.out.println(author.getName()));
    }
}
~~~

##### **⑨ findFirst**

获取流中的第一个元素。

例如：

~~~java
public class FindFirst {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 获取一个年龄最小的作家，并输出他的姓名
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
        first.ifPresent(author -> System.out.println(author.getName()));
    }
}
~~~

##### **⑩ reduce归并**

对流中的数据按照你指定的计算方式计算出一个结果。（缩减操作）

reduce的作用是把stream中的元素给组合起来，我们可以传入一个初始值，它会按照我们的计算方式依次拿流中的元素和初始化值进行计算，计算结果再和后面的元素计算。

reduce**两个参数的重载形式**内部的计算方式如下：

~~~java
T result = identity;
for (T element : this stream)
	result = accumulator.apply(result, element)
return result;
~~~

其中identity就是我们可以通过方法参数传入的初始值，accumulator的apply具体进行什么计算也是我们通过方法参数来确定的。

例如：

~~~java
public class Reduce {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 使用reduce求所有作者年龄的和
        Integer sum = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, (result, element) -> result + element);
        System.out.println(sum);
         // 使用reduce求所有作者中年龄的最大值
        Integer max = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, ((integer, integer2) -> integer > integer2 ? integer : integer2));
        System.out.println(max);
        // 使用reduce求所有作者中年龄的最小值
        Integer min = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, ((integer, integer2) -> integer < integer2 ? integer : integer2));
        System.out.println(min);
    }
}
~~~

reduce**一个参数的重载形式**内部的计算

~~~java
// 相当于不用我们自定义初始值，而是用stream流中的第一个元素作为初始值
boolean foundAny = false;
T result = null;
for (T element : this stream) {
	if (!foundAny) {
        foundAny = true;
        result = element;
	}
	else
		result = accumulator.apply(result, element);
}
return foundAny ? Optional.of(result) : Optional.empty();
~~~

如果用一个参数的重载方法去求最小值代码如下：

~~~java
public class Reduce {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 使用reduce一个参数的重载方法求所有作者中年龄的最小值
        Optional<Integer> min2 = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce((integer, integer2) -> integer < integer2 ? integer : integer2);
        System.out.println(min2.get());
    }
}
~~~

### **3.5** **注意事项**

- 惰性求值（如果没有终结操作，没有中间操作是不会得到执行的）
- 流是一次性的（一旦一个流对象经过一个终结操作后。这个流就不能再被使用）
- 不会影响原数据（我们在流中可以多数据做很多处理，但是正常情况下是不会影响原来集合中的元素的，这往往也是我们期望的。除非你调用set方法修改对象的值，但一般不会有人写出这种代码）

## **4. Optional**

### **4.1** **概述**

我们在编写代码的时候出现最多的就是空指针异常。所以在很多情况下我们需要做各种非空的判断。

例如：

~~~java
Author author = getAuthor();
if(author!=null){
    System.out.println(author.getName());
}
~~~

尤其是对象中的属性还是一个对象的情况下。这种判断会更多。

而过多的判断语句会让我们的代码显得臃肿不堪。所以在JDK8中引入了Optional,养成使用Optional的习惯后你可以写出更优雅的代码来避免空指针异常。

并且在很多函数式编程相关的API中也都用到了Optional，如果不会使用Optional也会对函数式编程的学习造成影响

### **4.2** **使用**

#### **4.2.1** **创建对象**

Optional就好像是包装类，可以把我们的具体数据封装Optional对象内部。然后我们去使用Optional中封装好的方法操作封装进去的数据就可以非常优雅的避免空指针异常。

##### ① `ofNullable`

我们一般使用Optional的**静态方法ofNullable**来把数据封装成一个Optional对象。无论传入的参数是否为null都不会出现问题。

~~~java
Author author = GetAuthor.getAuthor();
Optional<Author> authorOptional = Optional.ofNullable(author);
~~~

你可能会觉得还要加一行代码来封装数据比较麻烦。但是如果改造下getAuthor方法，让其的返回值就是封装好的Optional的话，我们在使用时就会方便很多。

而且在实际开发中我们的数据很多是从数据库获取的。Mybatis从3.5版本可以也已经支持Optional了。我们可以直接把dao方法的返回值类型定义成Optional类型，MyBastis会自己把数据封装成Optional对象返回。封装的过程也不需要我们自己操作。

##### ② `of`

如果你**确定一个对象不是空**的则可以使用Optional的**静态方法of**来把数据封装成Optional对象。

```java
Author author = GetAuthor.getAuthor();
// 使用of创建Optional对象
Optional<Author> authorOptional1 = Optional.of(author);
```

但是一定要注意，如果使用of的时候传入的参数必须不为null，否则会抛出异常。

如果一个方法的返回值类型是Optional类型。而如果我们经判断发现某次计算得到的返回值为null，这个时候就需要把null封装成Optional对象返回。这时则可以使用Optional的**静态方法empty**来进行封装。

~~~java
Optional<Author> authorOptional2=author==null?Optional.empty():Optional.of(author);
~~~

显然使用`ofNullable`会更加方便，不用自己手动判断

#### **4.2.2** **安全消费值**ifPresent

我们获取到一个Optional对象后肯定需要对其中的数据进行使用。这时候我们可以使用其**ifPresent**方法对来消费其中的值。

这个方法会判断其内封装的数据是否为空，不为空时才会执行具体的消费代码。这样使用起来就更加安全了。

例如，以下写法就优雅的避免了空指针异常：

~~~java
Author author = GetAuthor.getAuthor();
Optional<Author> authorOptional = Optional.ofNullable(author);
authorOptional.ifPresent(author1 -> System.out.println(author1.getName()));
~~~

#### **4.2.3** **获取值**get

如果我们想获取值自己进行处理可以使用get方法获取，但是不推荐。因为当Optional内部的数据为空的时候会出现异常。

**4.2.4** **安全获取值**

如果我们期望安全的获取值。我们不推荐使用get方法，而是使用Optional提供的以下方法。

① `orElseGet`

获取数据并且设置数据为空时的默认值。如果数据不为空就能获取到该数据。如果为空则根据你传入的参数来创建对象作为默认值返回。

~~~java
Optional<Author> authorOptional = Optional.ofNullable(author);
authorOptional.orElseGet(() -> new Author());
~~~

② `orElseThrow`

获取数据，如果数据不为空就能获取到该数据。如果为空则根据你传入的参数来创建异常抛出。

~~~java
Optional<Author> authorOptional = Optional.ofNullable(author);
// 使用orElseThrow，在为空时抛出指定异常
try {
    authorOptional.orElseThrow((Supplier<Throwable>) () -> new RuntimeException("author为空"));
} catch (Throwable e) {
    throw new RuntimeException(e);
}
~~~

#### **4.2.5** **过滤**

我们可以使用filter方法对数据进行过滤。如果原本是有数据的，但是不符合判断，也会变成一个无数据的Optional对象，而不是null。

~~~java
public class Filter {
    public static void main(String[] args) {
        Author author = GetAuthor.getAuthor();
        Optional<Author> authorOptional = Optional.ofNullable(author);
        authorOptional.ifPresent(author1 -> System.out.println(author1.getAge()));
        authorOptional.filter(author12 -> author12.getAge()>50).ifPresent(author1 -> System.out.println(author1.getAge()));
    }
}
~~~

#### **4.2.6** **判断**

我们可以使用isPresent方法进行是否存在数据的判断。如果为空返回值为false,如果不为空，返回值为true。但是这种方式并不能体现Optional的好处，**更推荐使用ifPresent**方法。

#### **4.2.7** **数据转换**

Optional还提供了map可以让我们的对数据进行转换，并且转换得到的数据也还是被Optional包装好的，保证了我们的使用安全。

例如我们想获取作家的书籍集合。

~~~java
public class OptionalMap {
    public static void main(String[] args) {
        Author author = GetAuthor.getAuthor();
        Optional<Author> authorOptional = Optional.ofNullable(author);
        Optional<List<Book>> books = authorOptional.map(author1 -> author1.getBooks());
        books.ifPresent(books1 -> System.out.println(books1));
    }
}
~~~

## **5.** **函数式接口**

### **5.1** **概述**

**只有一个抽象方法**的接口我们称之为函数接口。

JDK的函数式接口都加上了**@FunctionalInterface** 注解进行标识。但是无论是否加上该注解只要接口

中只有一个抽象方法，都是函数式接口。

### **5.2** **常见函数式接口**

#### Consumer 消费接口

根据其中抽象方法的参数列表和返回值类型知道，我们可以在方法中对传入的参数进行消费。

#### Function 计算转换接口

根据其中抽象方法的参数列表和返回值类型知道，我们可以在方法中对传入的参数计算或转换，把结果返回

#### Predicate 判断接口

根据其中抽象方法的参数列表和返回值类型知道，我们可以在方法中对传入的参数条件判断，返回判断结果

#### Supplier 生产型接口

根据其中抽象方法的参数列表和返回值类型知道，我们可以在方法中创建对象，把创建好的对象返回

### **5.3** **常用的默认方法**

#### and

我们在使用Predicate接口时候可能需要进行判断条件的拼接。而and方法相当于是使用&&来拼接两个判断条件

#### or

我们在使用Predicate接口时候可能需要进行判断条件的拼接。而or方法相当于是使用||来拼接两个判断条件。

#### negate

Predicate接口中的方法。negate方法相当于是在判断添加前面加了个! 表示取反。

