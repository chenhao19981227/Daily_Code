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

