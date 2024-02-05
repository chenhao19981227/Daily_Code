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

