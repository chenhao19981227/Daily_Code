# Effective Java随记

# 一、创建和销毁对象

## 1.1 用静态工厂方法代替构造器

对于类而言，为了让客户端获取它自身的一个实例，最传统的方法就是提供一个公有的构造器 还有一种方法，也应该在每个程序员的工具箱中占有一席之地。类可以提供一个公有的静态工厂 方法（ static factory method ），它只是一个返回类的实例的静态方法。

比如：

~~~java
public class Cat {
    public Cat(){}
    public static Cat getInstance(){// 静态工厂方法
        return new Cat();
    };
}

public class Test {
    public static void main(String[] args) {
        Cat cat1=new Cat();
        Cat cat2 = Cat.getInstance();
    }
}
~~~

我们可以通过在Cat类中提供`getInstance`方法来返回Cat的实例。

如果不通过公有的构造器 或者说除了公有的构造器之外，类还可以给它的客户端提供静态工厂方法 提供静态工厂方法而不是公有的构造器，这样做既有优势，也有劣势。

### 优势：

#### ① 可以见名知义

如果构造器的参数本身没有确切地描述正被返回的对象，那用户只通过类名是没办法知道具体返回的是个什么样的实例。那么具有适当名称的静态工厂方法会更有优势，产生的客户端代码也更易于阅读。

比如：

~~~java
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    boolean isMale;
    String name;
    public static Cat getTomcat(String name){
        return new Cat(true,name);
    }
}

public class Test {
    public static void main(String[] args) {
        Cat tomcat1 = new Cat(true, "cat");
        Cat tomcat2=Cat.getTomcat("cat");
    }
}
~~~

用户可以直接通过`getTomcat`这一方法名知道会返回一只公猫，而不用通过自己去理解参数，并传入正确的参数来创建公猫实例。

#### ② 可以实现单例

可以使用预先构建好的实例，或者将构建好的实例缓存起来，进行重复利用，从而避免创建不必要的重复对象。

比如：

~~~java
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    int id;
    boolean isMale;
    String name;
    private static Map<String,Cat> catMap=new HashMap<>();
    public static Cat getNameNoRepeat(String name,Boolean isMale) {// 限制名字不能重复
        return catMap.computeIfAbsent(name,key->new Cat(1,isMale,key));
    }
}

public class Test {
    public static void main(String[] args) {
        Cat singleNameCat1=Cat.getNameNoRepeat("kitty",true);
        Cat singleNameCat2=Cat.getNameNoRepeat("kitty",true);
        System.out.println(singleNameCat1==singleNameCat2);// true
    }
}
~~~

通过使用静态工厂方法，我们不仅可以实现单例，还可以根据自己需求灵活实现单例，比如名字不能重复、id不能重复等。

而如果使用`new Cat()`，我们必定得到不同的实例。

#### ③ 可以返回子类型的对象

类的构造器本身是无法使用return语句的，这是构造器本身的性质决定的，因此只能返回这个类本身的实例。

使用静态工厂方法则可以返回该类的任意子类对象实例，比较灵活。

同时，这种灵活性的一种应用是，API 可以返回对象，同时又不会使对象的类变成公有的。以这种方式隐藏实现类会使API变得非常简洁。也就是说，用户可以不知道你有什么子类，我通过静态工厂方法的名字知道返回给我的对象有什么用就好。

比如：

~~~java
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    private static class SpyCat extends Cat{}
    public static Cat getSpyCat(){
        return new SpyCat();
    }
}

public class Test {
    public static void main(String[] args) {
        Cat spyCat = Cat.getSpyCat();
    }
}
~~~

如果直接在Test中`new SpyCat()`，是会报错的，因为他是`Private`的。

**这项技术适用于基于接口的框架。？？？不太理解**

#### ④ 根据参数值返回不同对象

只要是已声明的返回类型的子类型，都是允许的。返回对象的类也可能随着发行版本的不同而不同。

~~~java
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    public static Cat getCatByNameLength(String name){
        return name.length()>5?new LongNameCat():new ShortNameCat();
    }
}

public class Test {
    public static void main(String[] args) {
        // class effective_java.chapter2_creating_and_destroying_objects.item01.ShortNameCat
        System.out.println(Cat.getCatByNameLength("tom").getClass());
        // class effective_java.chapter2_creating_and_destroying_objects.item01.LongNameCat
        System.out.println(Cat.getCatByNameLength("CrookShanks").getClass());
    }
}
~~~

#### ⑤ 方法返回的对象所属的类，在编写包含该静态工厂方法的类时可以不存在

这点从概念上看比较难以理解，但这点非常重要，他是服务提供者框架（如`JDBC`）的基础。从Java 6开始， Java平台就提供了一个通用的服务提供者框架`Java.util.ServiceLoader`。只不过`JDBC`的出现早于`ServiceLoader`，因此没用`ServiceLoader`实现。

我们通过下面这个例子来理解这一优点：

~~~java
// 提供一个服务接口，接口里有一个抽象方法execute
public interface Service {
    void execute();
}
~~~

~~~java
// 这是一个用来注册和创建服务的类
public class CatServices {
    // 使用map保存注册过的服务
    private static final Map<String, Supplier<Service>> providers =new HashMap<>();
    public static final String DEFAULT_PROVIDER_NAME = "provider";
    public static void registerDefaultProvider(Supplier<Service> provider) {
        registerProvider(DEFAULT_PROVIDER_NAME, provider);
    }
    public static void registerProvider(String name, Supplier<Service> provider) {
        providers.put(Objects.requireNonNull(name), Objects.requireNonNull(provider));
    }
    public static Service newInstance(){
        return newInstance(DEFAULT_PROVIDER_NAME);
    }
    // 确保用户要求创建的服务是已经注册过的
    public static Service newInstance(String name) {
        Supplier<Service> provider = providers.get(name);
        if(provider==null){
            System.out.println("没有这个服务！");
        }
        assert provider != null;
        return provider.get();
    }
}
~~~

到目前为止，我们一个具体的服务类都没有实现，只是单纯有一个服务的接口，以及创建服务的静态方法。这就是书中所说的：方法返回的对象所属的类，在编写包含该静态工厂方法的类时可以不存在。也就是说，我们在编写`CatServices`时，对于未来用户可能会用它来创建什么服务，我们不清楚，但是完全不影响。

现在，有一家店想要为猫猫提供一个吃饭服务，它实现了`Service`接口，并实现`execute()`方法

~~~java
public class EatService implements Service{
    @Override
    public void execute() {
        System.out.println("为小猫提供吃饭服务");
    }
}
~~~

然后，他们到`CatService`中注册他们的服务。在注册完服务后，我们就可以创建服务实例，并调用。

~~~java
public class Test {
    public static void main(String[] args) {
        // 注册吃饭服务
        CatServices.registerProvider("eat",EatService::new);
        // 创建服务实例
        EatService eat = (EatService) CatServices.newInstance("eat");
        // 使用服务
        eat.execute();
    }
}
~~~

可以看到，方法返回了`EatService`类的对象，但是`EatService`类在我们编写`CatServices`时还未存在。但通过使用静态工厂方法和服务提供者注册机制，我们可以轻松地在运行时创建和使用 `EatService` 的实例，而无需在编写 `CatServices` 类时就知道 `EatService` 的存在。这为延迟绑定（即在运行时而非编译时绑定实现类）和服务的模块化提供了极大的灵活性。未来如果还有更多的服务被创建，我们也无需修改任何代码。

### 劣势

当然，静态工厂方法也不是一本万利的，也有一些缺陷

#### ① 类如果不含公有的或者受保护的构造器，就不能被子类化

有时候为了让用户使用静态构造方法来创建对象，而不是构造器，可能会将构造器私有化

~~~java
private Cat(){}
~~~

这样做的后果是，该类不能拥有子类。因为每个类都需要有一个构造器，而子类的构造器中是会通过`Super()`来调用父类构造器的。而如果父类构造器私有，则无法调用。

但也因此，对于构造器不可见的类，书中更鼓励使用复合而不是继承。这个在后续会讲到。

#### ② 静态工厂方法对于用户来说不易发现

虽然静态工厂方法可以通过命名明确其含义，但是对于用户来说，要知道存在这样一个方法，需要上网搜索或者自己查阅API文档，这远不如使用构造器来得方便。

不过，我们可以通过同一命名风格来改善这一点。

书中给出了静态工厂方法一些惯用名称，这里只列出了其中的一小部分：

- `from` —— 类型转换方法，它接受单个参数并返回此类型的相应实例，例如：`Date d = Date.from(instant);`
- `of` —— 聚合方法，接受多个参数并返回该类型的实例，并把他们合并在一起，例如：`Set faceCards = EnumSet.of(JACK, QUEEN, KING);`
- `valueOf` —— from 和 to 更为详细的替代 方式，例如：`BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);`
- `instance` 或 `getinstance` —— 返回一个由其参数 (如果有的话) 描述的实例，但不能说它具有相同的值，例如：`StackWalker luke = StackWalker.getInstance(options);`
- `create` 或 `newInstance` —— 与 instance 或 `getInstance` 类似，除此之外该方法保证每次调用返回一个新的实例，例如：`Object newArray = Array.newInstance(classObject, arrayLen);`
- `getType` —— 与 `getInstance` 类似，但是在工厂方法处于不同的类中的时候使用。`getType`中的 Type 是工厂方法返回的对象类型，例如：`FileStore fs = Files.getFileStore(path);`
- `newType` —— 与 `newInstance` 类似，但是在工厂方法处于不同的类中的时候使用。`newType`中的 Type 是工厂方法返回的对象类型，例如：`BufferedReader br = Files.newBufferedReader(path);`
- `type` —— `getType` 和 `newType` 简洁的替代方式，例如：`List litany = Collections.list(legacyLitany);`

## 1.2 遇到多个构造器参数时要考虑使用构建器

静态工厂和构造器有个共同的局限性：它们都不能很好的扩展到大量的可选参数。面对这种情况，我们有以下几种解决思路，但都有利有弊。

### ① 生成大量构造方法

比如下面的例子，猫类有5个属性。在构造其实例时，这几种属性有多种组合，比如可以没有名字，也可以没有主人，或者两者都无。

单是如此就有3种组合方式，若属性进一步增多，组合方式也就更多，我们不得不为每一种组合方式提供一个构造方法，这会使得代码十分臃肿。且对于用户来说，大量的参数大大降低了构建对象的“体验”。

~~~java
public class Cat1 {
    private Integer id;// id，唯一，不可变，不可为null
    private String name;// 名字，不唯一，可变，可为null
    private String color;// 颜色，不唯一，不可变，可为null
    private LocalDateTime birthday;// 生日，不唯一，不可变，不可为null
    private Integer masterId;// 主人，不唯一，可变，可为null
    
    public Cat1(){}
    public Cat1(Integer id, String name, String color, LocalDateTime birthday, Integer masterId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.birthday = birthday;
        this.masterId = masterId;
    }
    public Cat1(Integer id, String color, LocalDateTime birthday, Integer masterId) {
        this.id = id;
        this.color = color;
        this.birthday = birthday;
        this.masterId = masterId;
    }
    public Cat1(Integer id, String name, String color, LocalDateTime birthday) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.birthday = birthday;
    }
    public Cat1(Integer id, String color, LocalDateTime birthday) {
        this.id = id;
        this.color = color;
        this.birthday = birthday;
    }
}
~~~

当然，这种方法也有一定优点，其性能相对构建器来说会好一点。不过在日常开发中，这点性能无足轻重。

### ② 使用`setter()`注入属性

~~~java
public class Test {
    public static void main(String[] args) {
        Cat1 cat1=new Cat1();
        cat1.setId(1);
        cat1.setName("mao");
    }
}
~~~

我们可以使用空参构造器、某个特定的构造器或者某个静态方法获取对象，然后通过`setter()`方法注入我们需要的属性。这样一来，就无需定义大量的构造方法，使创建实例变得容易，这样产生的代码读起来也很容易。

虽然听起来很不错，但是，因为构造过程被分到了几个调用中，在构造过程中 Java Bean 可能处于不一致的状态，类无法仅仅通过检验构造器参数的有效性来保证一致性。	

并且由于提供了`setter()`，对应的属性将不再“不可变”，这就需要程序员付出额外的努力来确保它的线程安全。‘

### ③ 建造者(Builder)模式

它不直接生成想要的对象，而是让客户端利用所有必要的参数调用构造器（或者静态工厂），得到一个`builder`对象 然后客户端在 `builder`对象上调用类似于`setter()`的方法，来设置每个相关的可选参数。最后，客户端调用无参`build`方法来生成通常是不可变的对象。这个`builder`通常是它构建的类的静态成员类。

例子如下：

我们在`Cat2`类中创建一个静态内部类`CatBuilder`，`CatBuilder`拥有`Cat2`所有的属性。

然后在`CatBuilder`中，我们创建一个构造器，构造器中的参数为`Cat2`中不可为null的属性。

接着，对其他所有参数，我们建立一个方法用于注入属性，该方法返回一个`CatBuilder`对象。

最后在构建一个返回`Cat2`对象的`build`方法即可。

~~~java
@ToString
public class Cat2 {
    private final Integer id;// id，唯一，不可变，不可为null
    private String name;// 名字，不唯一，可变，可为null
    private String color;// 颜色，不唯一，不可变，可为null
    private final LocalDateTime birthday;// 生日，不唯一，不可变，不可为null
    private Integer masterId;// 主人，不唯一，可变，可为null

    public Cat2(Integer id, LocalDateTime birthday) {
        this.id = id;
        this.birthday = birthday;
    }
    public static class CatBuilder{
        private final Integer id;// id，唯一，不可变，不可为null
        private String name;// 名字，不唯一，可变，可为null
        private String color;// 颜色，不唯一，不可变，可为null
        private final LocalDateTime birthday;// 生日，不唯一，不可变，不可为null
        private Integer masterId;// 主人，不唯一，可变，可为null
        public CatBuilder(Integer id,LocalDateTime birthday){
            this.id=id;
            this.birthday=birthday;
        }
        public CatBuilder name(String name){
            this.name=name;
            return this;
        }
        public CatBuilder color(String color){
            this.color=color;
            return this;
        }
        public CatBuilder masterId(Integer masterId){
            this.masterId=masterId;
            return this;
        }
        public Cat2 build(){
            return new Cat2(this);
        }
    }
    private Cat2(CatBuilder builder){
        id=builder.id;
        name=builder.name;
        color=builder.color;
        birthday=builder.birthday;
        masterId=builder.masterId;
    }
}

public class Test {
    public static void main(String[] args) {
        Cat2 cat2 = new Cat2.CatBuilder(2, LocalDateTime.now())
                .name("maomao")
                .color("white")
                .masterId(10)
                .build();
        System.out.println(cat2);
        // Cat2(id=2, name=maomao, color=white, birthday=2024-02-17T18:39:24.251, masterId=10)

    }
}
~~~

### ④ 使用`Lombok`

使用`Lombok`的`@Builder`注解，可以快速的创建构建器。

~~~java
@Builder
@AllArgsConstructor
@ToString
public class Cat3 {
    private final Integer id;// id，唯一，不可变，不可为null
    private String name;// 名字，不唯一，可变，可为null
    private String color;// 颜色，不唯一，不可变，可为null
    private final LocalDateTime birthday;// 生日，不唯一，不可变，不可为null
    private Integer masterId;// 主人，不唯一，可变，可为null

    public Cat3(Integer id, LocalDateTime birthday) {
        this.id = id;
        this.birthday = birthday;
    }
}

public class Test {
    public static void main(String[] args) {
        Cat3 cat3=new Cat3.Cat3Builder()
                .id(3)
                .birthday(LocalDateTime.now())
                .name("maomao")
                .color("white")
                .masterId(10)
                .build();
        System.out.println(cat3);
        // Cat3(id=3, name=maomao, color=white, birthday=2024-02-17T20:04:59.885, masterId=10)
    }
}
~~~

`Builder`模式的确也有它自身的不足。为了创建对象 ，必须先创建它的构建器，虽然创建这个构建器的开销在实践中可能不那么明显，但是在某些十分注重性能的情况下，可能就成问题了。

`Builder`模式还比重叠构造器模式更加冗 ，因此它只在有很多参数的时候才使用，比如4个或者更多个参数。但是记住，将来你可能需要添加参数，如果一开始就使用构造器或者静态 厂，等到类需要多个参数时才添加构造器，就会无法控制，那些过时的构造器或者静态工厂显得十分不协调。因此，通常最好一开始就使用构建器。

## 1.3 用私有构造器或者枚举类型强化 Singleton 属性

当我们想要一个单例对象时，通常有好几种做法，但首先最重要的是，要先把构造器私有化。

### ① 饿汉式

~~~java
public class Singleton1 {
    private Singleton1(){};
    private static final Singleton1 INSTANCE=new Singleton1();
    public static Singleton1 getInstance(){
        return INSTANCE;
    }
}
public class Test {
    public static void main(String[] args) {
        // 饿汉式
        System.out.println(Singleton1.getInstance()==Singleton1.getInstance());// true
    }
}
~~~

饿汉式在类初始化时，便直接创建出单例对象，因此需要用`final`修饰，适用于那些只要程序启动就一定会用到的单例对象。

### ② 懒汉式

~~~java
public class Singleton2 {
    private Singleton2(){
        // 防止通过反射构造对象
        if(INSTANCE!=null){
            throw new Error("singleton");
        }else {
            INSTANCE=this;
        }
    };
    private static volatile Singleton2 INSTANCE=null;
    public static Singleton2 getInstance(){
        if(INSTANCE==null){
            synchronized (Singleton2.class){
                if(INSTANCE==null){
                    INSTANCE=new Singleton2();
                }
            }
        }
        return INSTANCE;
    }
    // 防止通过反序列化构造对象
    public Object readResole(){
        return INSTANCE;
    }
}
public class Test {
    public static void main(String[] args) {
        // 懒汉式
        System.out.println(Singleton2.getInstance()==Singleton2.getInstance());// true
    }
}
~~~

懒汉式只有在需要用到该对象的时候才会尝试创建。

在多线程场景下，需要考虑并发问题，所以需要通过`volatile`和`synchronized`加以控制。

此外，为了防止一些恶意程序或者错误使用，我们需要防止用户通过反射以及反序列化的方式来创建对象。

### ③ 枚举类

~~~java
public enum Singleton3{
    INSTANCE;
}
public class Test {
    public static void main(String[] args) {
        // 枚举类
        System.out.println(Singleton3.INSTANCE==Singleton3.INSTANCE);// true
    }
}
~~~

在`JDK1.5`之后出现了枚举类。采用这种方法更加简洁，并且天然能防止反序列化和反射，即使是在面对复杂的序列化或者反射攻击的时候。虽然这种方法还没有广泛采用，但是单元素的枚举类型经常成为实现单例的最佳方法。

## 1.4 通过私有构造器强化不可实例化的能力

当需要一个类不可被实例化的时候，优先考虑将构造器私有化。

当然也有其他方法，比如将其声明为抽象类，当然，这种做法并不推荐。

不过，将构造器私有化也有坏处：它使得该类不能被子类化。因为子类天然继承父类的构造方法，但是由于构造方法私有化了，所以无法继承。

## 1.5 优先考虑依赖注入来引用资源

思考一下，当某个类需要引入一个底层资源的时候，我们该怎么做？这里要注意的是，底层资源与普通的属性不同，一般是声明为final的

首先，我们当然可以直接在类中定义属性的值。

~~~java
private static final Dog dog=new Dog("wang");
~~~

或者将属性定义为`nonfinal`，然后在需要的时候使用setter函数修改属性的值。

依赖注入模式在我们日常开发中经常使用，只不过我们可能不知道他叫这个名字

~~~java
public class Person {
    private final Dog dog;
    public Person(Dog dog){
        this.dog=dog;
    }
}
public class Test {
    public static void main(String[] args) {
        Dog dog=new Dog("wang");
        Person person=new Person(dog);
    }
}
~~~

实际上，就是在构造方法中传入了我们需要的对象或值而已，十分简单。

需要注意的点是，这个模式有一个常见的变体：将资源工厂传递给构造器。

所谓工厂，就是可以重复被调用来创建类型实例的一个对象，我们可以利用Java8提供的Supplier接口十分便捷地实现这一操作。

~~~java
public class DogFactory {
    public static Dog dogFactory(Supplier<? extends Dog> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {
        Dog dog = dogFactory(() -> new Dog("哈士奇"));
        System.out.println(dog.name);
    }
}
~~~

这种变体实际上就说设计模式中常用的工厂模式，具体优点可以参考设计模式相关书籍。

## 1.6 避免创建不必要的对象

**当你应该重用现有对象的时候，请不要创建新的对象**，比如字符串常量的创建。第二种创建方式会在堆区额外创建一个对象。

~~~java
String str = “hello”;
String str = new String(“hello”);
~~~

在实际开发中，为了防止重复创建，我们通常会将其声明为`static final`类型，或者使用static代码块对其进行加载，比如：

~~~java
public class RomanN {
    public boolean isRomanNumeral(String originString){
        return originString.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }
    private static final String ROMANREGEX = "^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";
    public boolean isRomanNumeral2(String originString){
        return originString.matches(ROMANREGEX);
    }
}
public class Test {
    public static void main(String[] args) {
        RomanN test=new RomanN();
        long start = System.currentTimeMillis();
        System.out.println(test.isRomanNumeral("test"));
        System.out.println(test.isRomanNumeral("test2"));
        System.out.println(test.isRomanNumeral("test3"));
        System.out.println(test.isRomanNumeral("VI"));
        System.out.println(System.currentTimeMillis()-start);// 2

        /**
         * 下面这种方式确实耗时更短
         */
        long start2 = System.currentTimeMillis();
        System.out.println(test.isRomanNumeral2("test"));
        System.out.println(test.isRomanNumeral2("test2"));
        System.out.println(test.isRomanNumeral2("test3"));
        System.out.println(test.isRomanNumeral2("VI"));
        System.out.println(System.currentTimeMillis()-start2);// 1
    }
}
~~~

上面的代码是一个判断字符串是否是一个罗马数字，采用正则表达式进行校验。

如果采用第一种方式，那么每次调用`isRomanNumeral`函数，都会创建一个Pattern实例，这是极其消耗空间和时间的。

因此最好将其静态化，成为类初始化的一部分。

还有一个常见的点：**自动装箱和拆箱**

这是java为我们提供的一个便利，但是装箱和拆箱的过程实际上就是对象的创建和销毁，我们要尽量避免这中情况。

~~~java
public class Test {
    public static void main(String[] args) {
        long start3=System.currentTimeMillis();
        sum1();
        System.out.println(System.currentTimeMillis()-start3);// 6977
        long start4=System.currentTimeMillis();
        sum2();
        System.out.println(System.currentTimeMillis()-start4);// 524
    }
    private static void sum1(){
        Long sum=0L; // 包装类型Long
        for(long i=0;i<=Integer.MAX_VALUE;i++){
            sum+=i;
        }
    }
    private static void sum2(){
        long sum=0L; // 基本数据类型long
        for(long i=0;i<=Integer.MAX_VALUE;i++){
            sum+=i;
        }
    }
}

~~~

可以看到，在`sum1()`中，由于`sum`声明为`Long`类型，因此每次运行`sum+=i`时，jvm都会帮我们自动装箱，导致最终消耗了大量时间。可以看到，运行时间相差了十几倍。

## 1.7 消除过期的对象引用

Java有垃圾回收机制，那么还存在内存泄露吗？答案是肯定的，所谓的垃圾回收GC会自动管理内存的回收，而不需要程序员每次都手动释放内存，但是如果存在大量的临时对象在不需要使用时并没有取消对它们的引用，就会吞噬掉大量的内存，很快就会造成内存溢出。比如：

### ① 过期引用

~~~java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements=new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++]=e;
    }

    public Object pop() {
        if(size==0)
            throw new EmptyStackException();
//        return elements[--size]; // 没有清除引用，容易造成内存泄漏
        Object result=elements[--size];
        elements[size]=null; // 手动清除引用
        return result;
    }

    /**
     * Ensure space for at least one more element, roughly doubling the capacity
     * each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
~~~

### ② 缓存

一旦将对象引用放入缓存中，很容易忘记它的存在，并且在它变得无关紧要之后，仍然保留在缓存中。即使外部没有指向value的引用，但`Hashmap`中依然保存了指向value的强引用。随着缓存的积累很大概率会出现溢出的可能。因此，如果你正好想实现一个缓存：只要在缓存之外不存在对某个项（entry）的键（key）引用，那该项就会被自动清除，就可以用`WeakHashMap`来表示缓存：

~~~java
public class Cache {
    public static void main(String[] args) throws InterruptedException {
        // 使用HashMap作为缓存
        Map<Object, String> hashMapCache = new HashMap<>();
        // 使用WeakHashMap作为缓存
        Map<Object, String> weakHashMapCache = new WeakHashMap<>();

        // 创建一个键对象
        Object hashMapKey = new Object();
        Object weakHashMapKey = new Object();

        // 添加对象到HashMap和WeakHashMap
        hashMapCache.put(hashMapKey, "HashMapValue");
        weakHashMapCache.put(weakHashMapKey, "WeakHashMapValue");

        // 移除强引用
        hashMapKey = null;
        weakHashMapKey = null;

        // 请求垃圾收集
        System.gc();

        // 短暂延迟，等待垃圾收集器运行
        Thread.sleep(5000);

        // 打印缓存内容
        System.out.println("HashMap cache contains key? " + hashMapCache.containsValue("HashMapValue")); //true
        System.out.println("WeakHashMap cache contains key? " + weakHashMapCache.containsValue("WeakHashMapValue")); // false
    }
}
~~~

### ③ 监听器和其他回调

由于以前没学过监听器和回调，等学习到事件建听模式后再来补充。。。

## 1.8 避免使用终结方法和清除方法

在 Java 程序中，应尽量避免使用终结方法，因为它通常是不可预测的，也是很危险的，一般情况下是不必要的。清除方法没有终结方法那么危险，但仍然是不可预测、运行缓慢、一般情况也是不必要的。注意清除方法需要实现`AutoCloseable`接口。

### ① 缺点

- 终结方法和清除方法不能保证会被及时执行
- 终结方法和清除方法有性能损失，特别是终结方法。
- 终结方法有一个严重的安全问题。如果从构造器或者它的序列化对等体（如`readObject`和`readResolve`方法）抛出异常，恶意子类的终结方法就可以在构造了一部分的应该已经半途夭折的对象上运行。这个终结方法会将该对象的引用记录在一个静态域中，阻止它被垃圾回收。**从构造器抛出异常，应该足以防止对象继续存在；但有了终结方法的存在，这一点就做不到了**。
- 如果从终结方法中抛出未捕获的异常，该对象的终结过程也会被终止。此时，该对象处于破坏的状态，如果另一个线程企图使用这种被破坏的对象，则可能导致不确定行为。正常情况下，未捕获的异常将导致线程终止，并打印`StackTrace`，但是如果异常在终结方法中，则不会如此，甚至连警告都不会打印出来。

### ② 优点

- 当资源的所有者忘记调用它的 `close` 方法时，终结方法或者清除方法可以作为“安全网”。虽然这样做并不能保证终结方法或清除方法及时运行，但是在客户端无法正常结束操作的情况下，迟一点释放资源总比永不释放好。
- 在本地对等体（native peer）中使用终结方法。本地对等体是一个本地（非 Java 的）对象（native object），普通对象通过本地方法（native method）委托给一个本地对象。因为本地对等体不是一个普通对象，所以垃圾回收器不会知道它，当它对应的 Java 对等体被回收的时候，它不会被回收。如果本地对等体没有关键资源，并且性能也可以接受的话，那么清除方法或者终结方法正是执行这项任务最合适的工具。如果本地对等体拥有必须被及时终止的资源，或者性能无法接受，那么该类就应该具有一个 `close` 方法。

总结，不要使用终结方法，清除方法仅作为一个安全网使用，不能指望其及时执行。当需要清理资源时最好还是使用手动清除。

### ③ 清除方法使用示例

~~~java
public class Room implements AutoCloseable{ // 实现AutoCloseable，并重写close()方法
    private static final Cleaner cleaner=Cleaner.create();
    private static class State implements Runnable{
        int numJunkPiles;
        State(int numJunkPiles){
            this.numJunkPiles=numJunkPiles;
        }
        @Override
        public void run() {
            System.out.println("Clean room");
            numJunkPiles=0;
        }
    }
    private final State state;
    private final Cleaner.Cleanable cleanable;
    public Room(int numJunkPiles) {
        state=new State(numJunkPiles);
        this.cleanable = cleaner.register(this,state); // 注册一个cleanable
    }
    @Override
    public void close() throws Exception {
        cleanable.clean();// 调用了state的run()方法
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        try (Room room1= new Room(10)) {
            System.out.println("ok");
        }
        System.out.println("——————————————————————————");// 成功清除

        Room room2  = new Room(99);
        room2.close();
        System.out.println("ok");
        System.out.println("——————————————————————————");// 成功清除

        Room room3  = new Room(99);
        room3=null;
        System.out.println("ok");
        System.out.println("——————————————————————————");// 清除失败

        Room room4=new Room(100);
        room4=null;
        System.gc();
        System.out.println("ok");// 成功清除

    }
}
~~~

实际上，在room3中我们没有用任何手段关闭资源，我们期望在没有指针指向该实例之后，清除方法能自动帮我们回收，但是结果并没有，这正是前面提到的不可预见性。

## 1.9 try-with-resources优先于try-finally

Java 类库中包括了许多必须通过调用`close`方法来手动关闭的资源。都是一些关于文件流、SQL连接。虽然其中很多资源都是用`finalizer()`方法作为最后的后盾，但是效果并没有很理想，原因见1.8。所以一般我们都会用try-finally来确保资源正确关闭。实际上，使用try-finally是能确保资源被正确关闭的。但是，有的时候我们不仅仅要求出现异常时资源被正确关闭，还有其他需求。

### ① 异常信息

有的时候我们不仅仅要求出现异常时资源被正确关闭，还需要知道哪里出现异常。这是try-finally无法做到的，比如：

~~~java
public class Finally {
    public static void main(String[] args) throws IOException {
        String line = readFile(""); // 9
        System.out.println(line);
    }

    private static String readFile(String path) throws IOException {
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(path));
            return br.readLine();
        }finally {
            br.close(); // 19
        }
    }
}

Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.io.BufferedReader.close()" because "br" is null
	at effective_java.chapter2_creating_and_destroying_objects.item09.Finally.readFile(Finally.java:19)
	at effective_java.chapter2_creating_and_destroying_objects.item09.Finally.main(Finally.java:9)
~~~

可以看到，如果设备出现异常，那么那么调用`readLine()`就会抛出异常，同时`close()`方法也出现异常，在这种情况下，`close()`异常会完全抹去`readLine()`异常。在异常堆栈轨迹中也完全没有`readLine()`异常的记录。这对于程序员来说很不好，因为我们无法根据异常信息进行排查。

~~~java
public class Resource {
    public static void main(String[] args) throws IOException {
        String line = readFile("");
        System.out.println(line);
    }

    private static String readFile(String path) throws IOException {
        try(BufferedReader reader=new BufferedReader(new FileReader(path))){
            return reader.readLine();
        }
    }
}

Exception in thread "main" java.io.FileNotFoundException: 
	at java.base/java.io.FileInputStream.open0(Native Method)
	at java.base/java.io.FileInputStream.open(FileInputStream.java:216)
	at java.base/java.io.FileInputStream.<init>(FileInputStream.java:157)
	at java.base/java.io.FileInputStream.<init>(FileInputStream.java:111)
	at java.base/java.io.FileReader.<init>(FileReader.java:60)
	at effective_java.chapter2_creating_and_destroying_objects.item09.Resource.readFile(Resource.java:14)
	at effective_java.chapter2_creating_and_destroying_objects.item09.Resource.main(Resource.java:9)
~~~

使用try-with-resources的话，如果`readLine()`和不可见的close方法都抛出异常，`close()`方法抛出的异常就会被禁止，try-finally处理机制中我们无法看到，堆栈轨迹中也不能打印，但是try-with-resources不一样，全部会被打印在堆栈轨迹中，并注明它们是被禁止的异常，通过编写调用`getSuppressed()`方法还可以访问到它们。

### ② 代码简洁

当有多个资源需要关闭时，使用try-finally就需要多重嵌套，这会使得代码看上去非常复杂

~~~java
public class Finally {
    static void copy(String src, String desc) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(desc);
            byte[] bytes = new byte[1024];
            int n;
            try {
                while ((n = in.read(bytes)) != -1) {
                    out.write(bytes, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
}
~~~

而使用try-with-resources的话，就能将需要关闭的资源都包裹在`try()`中，使得代码非常简洁

~~~java
public class Resource {
    static void copy(String src, String desc) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(desc)) {
            byte[] bytes = new byte[1024];
            int n;
            while ((n = in.read(bytes)) != -1) {
                out.write(bytes, 0, n);
            }
        }
    }
}
~~~

# 二、对于所有对象都通用的方法

## 2.1 覆盖equals时请遵守通用约定

### 2.1.1 不需要重写的情况

首先我们要知道，重写equals方法看起来非常的简单，但是很多的重写方式会导致错误的产生，并且后果很严重。所以避免这类问题的办法其实就是不去重写，让每个类的实例只和自身相等。下面这些情况下我们不需要重写equals：

#### ① 类的每个实例本质上都是唯一的

举个栗子，一个代表某种行为活动而不代表值的类，Thread类，它就完全没有重写的必要。

#### ② 类没有必要提供“逻辑相等”的测试功能

如Pattern类，它可以去覆盖equals，用来检查两个实例是否代表同一个正则，但设计者觉得用户不需要这个功能，所以没有进行重写。

#### ③ 父类已经重写了equals，而且父类的行为适用于子类

如大多的`Set`都从`AbstractSet`继承equals实现，`List`从`AbstractList`继承，`Map`从`AbstractMap`继承。父类的equals实现已经完全够用，且适合子类，所以没有必要重写。

#### ④ 确定equals方法永远不会被调用

类是私有或包级私有的，可以确定其equals方法永远不会被调用。

### 2.1.2 需要重写的情况

**当这个类为“值类”的时候**

`值类`：这个类具有逻辑相等的概念，仅仅表示一个或多个值，且父类没有重写equals方法。

如：`Integer`、`String`和业务中定义的某些POJO类，我们在比较的时候并不关心指针是否指向同一个对象，我们只想知道它们在逻辑上是否相等。

不过有一种值类无需覆盖equals，即枚举类型。因为它用受控实例确保每个值至多存在一个对象。

### 2.1.3 重写的规范

在重写的时候，必须要遵守它的通用约定，以确保程序不会发生严重的错误：

- 自反性：对于非null引用值x，`x.equals(x)`必须返回true；

- 对称性：对于非null引用值x、y，当且仅当`y.equals(x)`为true时，`x.equals(y)`返回true；

- 传递性：对于非null引用值x、y、z，若`x.equals(y)`为true，`y.equals(z)`为true，则`x.equals(z)`也为true；

- 一致性：对于非null引用值x、y，只要equals进行比较时用到的实例信息没有被修改，那么多次调用的返回值一致；

- 非空性：对于非null引用值x，`x.equals(null)`必须返回false。

### 2.1.4 重写的技巧

- 使用 == 操作符检查“参数是否可能为这个对象的引用”。在某些情况下，调用equals方法的成本可能是很高的，那么直接比较地址可能更为有效的得出结果；

- 使用`instanceof`操作符检查“参数是否为正确的类型”。不仅可以处理掉null值，也可以为接下来的类型转换规避`ClassCastException`异常；

- 把参数转换成正确类型。因为进行了`instanceof`的判断，所以转换会确保成功；

- 对于类中的关键属性，检查参数中的属性是否匹配


~~~java
public final class PhoneNumber {
    private final int areaCode,prefix,lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode,999,"area code");
        this.prefix = rangeCheck(prefix,999,"prefix");
        this.lineNum = rangeCheck(lineNum,9999,"line num");
    }
    private static int rangeCheck(int val,int max,String arg){
        if(val<0||val>max){
            throw new IllegalArgumentException(arg+":"+val);
        }
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)return true;
        if(!(obj instanceof PhoneNumber)) return false;
        PhoneNumber phoneNumber = (PhoneNumber) obj;
        return phoneNumber.areaCode==this.areaCode&&phoneNumber.prefix==this.prefix
                &&phoneNumber.lineNum==this.lineNum;
    }
}

public class Test {
    public static void main(String[] args) {
        PhoneNumber phoneNumber1=new PhoneNumber(86,0663,1234);
        PhoneNumber phoneNumber2=new PhoneNumber(86,0663,1234);
        System.out.println(phoneNumber1.equals(phoneNumber2)); // true
    }
}
~~~

### 2.1.5 需要注意的点

- 重写`equals`时一定要重写`hashcode`；
- 别把`equals`方法搞得太智能，简单做属性的比对就可以了；
- 别把`equals`方法中的`Object`入参替换为其它类型（**因为这是重载不是重写**）；

编写`equals`和`hashcode`方法是十分繁琐的，所以现在往往用现成的工具，如Google的`AutoValue`或者`lombok`，亦或是直接使用IDE自带的代码生成。

## 2.2 覆盖equals时总要覆盖hashCode

当我们覆盖了`equals`时，总要覆盖`hashCode`，举个例子：

~~~java
public class Person {
    String name;
    String id;
}
~~~

理论上来说，两个对象的名字和身份证如果一样的话，这两个人就应该是同一个人。

~~~java
public static void main(String[] args) {
    Person person1=new Person("a","1");
    Person person2=new Person("a","1");
    System.out.println(person1.equals(person2)); // false
}
~~~

因此我们需要覆盖原本的`equals`，但是当我们使用Map的时候，其存放位置是根据对象的`hashCode`进行计算的，此时对于我们来说`person1`和`person2`是同一个人，但是对于Map来说，这是两个不同的对象，是可以一起存入Map中的，这就出现了矛盾。因此我们在覆盖`equals`时总要覆盖`hashCode`。

**如何覆盖`hashcode`?**

~~~java
public class PhoneNumber {
    private final int areaCode,prefix,lineNum;

    @Override
    public int hashCode(){ // 手动覆盖，性能较好
        int result=Integer.hashCode(areaCode);
        result=31*result+Integer.hashCode(prefix);
        result=31*result+Integer.hashCode(lineNum);
        return result;
    }

    public int hashCode2(){ // Objects自带的，性能一般
        return Objects.hash(areaCode,prefix,lineNum);
    }
    private int hashCode;
    public int hashCode3(){ // 对于不可变的类，且hashCode计算开销大，可将其缓存在对象内部
        int result=hashCode;
        if(result==0){
            result=Integer.hashCode(areaCode);
            result=31*result+Integer.hashCode(prefix);
            result=31*result+Integer.hashCode(lineNum);
        }
        return result;
    }
}
~~~

当然，通常来说，我们可以使用框架自带的覆盖hashCode注解。

## 2.3 始终要覆盖同String

### 2.3.1 为什么要始终覆盖 toString

- 遵守 `toString` 约定并不像遵守 `equals` 和 `hashCode` 的约定那么重要。但是，提供好的 `toString` 方法可以使类用起来更加舒适，使用这个类的系统也更易于调试。

### 2.3.2 覆盖 toString 时的约定

- 在实际应用中，`toString`方法应该返回对象中包含的所有值得关注的信息。
  - 对于**值类**，建议在文档中指定返回值的格式
  - 如果指定了格式，通常最好再提供一个相匹配的静态工厂或者构造器，以便程序员可以很容地在对象及其字符串表示法之间来回转换。
- 无论是否决定指定格式，都应该在文档中明确地表明你的意图。
- 不论是否指定格式，都为`toString`返回值中包含的所有信息提供一种可以通过编程访问的模式

## 2.4 谨慎地覆盖clone

调用clone()方法需要对象实现Cloneable接口,该接口决定了Object中受保护的clone方法的实现行为：

- 如果一个类实现了Cloneable接口，Object的clone方法返回该对象的逐域拷贝；
- 如果一个类未实现Cloneable接口，则该对象就会抛出CloneNotSupportedException异常。

对于Cloneable接口，**它改变了超类中受保护的方法的行为。**

### 2.4.1 clone 方法规范

如果实现Cloneable接口是要对某个类起到作用，类和它的所有超类都必须遵守一个相当复杂的、不可实施的，并且基本上没有文档说明的协议。由此得到一种语言之外的机制：**无需调用构造器就可以创建对象。**

clone 方法的通用约定是非常弱的，下面是摘自 Object 规范中的约定内容

Clone方法用于创建和返回对象的一个拷贝，一般含义如下：

1、对于任何对象x，表达式`x.clone()!=x` 将会是true，并且表达式 `x.clone().getClass() == x.getClass()`将会是true，**但这不是绝对要求**。

2、通常情况下，表达式`x.clone.equals(x)`将会是true，**同1一样这不是绝对要求**。

### 2.4.2 存在的问题

#### ① 绕过构造器存在风险

比如下面的代码：

~~~java
public class User implements Cloneable{
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("调用构造器");
    }
    public User() {
    }
    @Override
    public User clone(){
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Test {
    public static void main(String[] args) {
        User u1=new User(1,"c"); // 调用构造器
        User u2 = u1.clone();
        System.out.println(u1);  // User(id=1, name=c)
        System.out.println(u2);  // User(id=1, name=c)
    }
}
~~~

可以看到，构造器只被调用了一次，也就是说，clone方法可以绕过构造器，因此，他也可以破坏单例模式。

#### ② 浅拷贝问题

`super.clone()`，这个操作主要是来做一次浅拷贝，他会把原对象完整的拷贝过来包括其中的引用。这样会带来问题，如果里面的某个属性是个可变对象，那么原来的对象改变，克隆的对象也跟着改变。所以在调用完`super.clone()`后，一般还需要重新拷贝可变对象。比如：

~~~java
public class SubUser extends User {
    String[] address;

    public SubUser(int id, String name, String[] address) {
        super(id, name);
        this.address = address;
    }

    public SubUser(String[] address) {
        this.address = address;
    }

    @Override
    public SubUser clone() {
        return (SubUser) super.clone();
    }
}

public class Test {
    public static void main(String[] args) {
        SubUser s1=new SubUser(2,"b",new String[]{"xx","cc"});
        SubUser s2 = s1.clone();
        s2.address[0]="dd";
        System.out.println(s1.address[0]); // dd
    }
}
~~~

正确的做法应该是：

~~~java
@Override
public SubUser clone() {
    SubUser user = (SubUser) super.clone();
    user.address=address.clone();
    return user;
}
~~~

在实践中，我们一般会假设：如果扩展一个类，并在子类中调用了`super.clone`，返回的对象就将是该子类的实例(我们要克隆的是子类而不是父类)。

超类提供此功能的唯一途径是：**返回一个通过调用`super.clone`而得到的对象**。**如果clone方法返回一个由构造器创建的对象，它就会得到错误的类(当前父类而不是想要的子类**)。 因此，如果你覆盖了非final类中的clone方法，则应该返回一个通过调用`super.clone`而得到的对象。如果类的所有超类都遵守这条规则，那调用`super.clone`方法最终会调用`Object.clone`方法，从而创建正确类的实例，此机制类似于自动的构造器调用链，只不过它不是强制要求的。

**综上：**

- 不可变的类永远都不应该提供 clone 方法
- `Cloneable` 结构与引用可变对象的 final 域的正常做法是不相兼容的。
- clone 方法是浅拷贝（只拷贝一层），对类所引用的对象需手动拷贝

#### ③ 链表

对于类似于链表结构的域，我们应当每一个节点都进行拷贝，而不是单纯的拷贝其头节点

### 2.4.3 更好的做法

提供一个拷贝构造器或拷贝工厂来代替 clone 方法 拷贝构造器：

~~~java
public class MyObject {
  public String field01;
 
  public MyObject() {
  }
 
  public static MyObject newInstance(MyObject object) {
    MyObject myObject = new MyObject();
    myObject.field01 = object.field01;
    return myObject;
  }
}
~~~

- 其不依赖于某一种很有风险的、语言之外的对象创建机制；
- 其不遵守尚未制定好的文档规范；
- 其不会与final域的正常使用发生冲突；
- 其不会抛出不必要的受检查异常；
- 其不需要类型转换；
- 采用其代替clone方法时，并没有放弃接口功能特性

## 2.5 考虑实现 Comparable 接口

`compareTo` 方法并没有在 `Object` 类中声明。 相反，它是 `Comparable` 接口中的唯一方法。 它与 Object 类的 equals 方法在性质上是相似的，除了它允许在简单的相等比较之外的顺序比较，它是泛型的。 通过实现 `Comparable` 接口，一个类表明它的实例有一个自然顺序（natural ordering）。 对实现 `Comparable` 接口的对象数组排序非常简单（如下），几乎 Java 平台类库中的所有值类以及所有枚举类型都实现了 `Comparable` 接口。

~~~java
Arrays.sort(a);
~~~

对于自定义的类，我们需要自己定义排序规则，以下是几条约定：

- 实现类必须确保所有 `x` 和 `y` 都满足 `sgn(x.compareTo(y)) == -sgn(y. compareTo(x))`。 （这意味着当且仅当 `y.compareTo(x)` 抛出异常时，`x.compareTo(y)` 必须抛出异常。）
- 实现类还必须确保该关系是可传递的：`(x. compareTo(y) > 0 && y.compareTo(z) > 0)` 意味着 `x.compareTo(z) > 0`。
- 最后，对于所有的 z，实现类必须确保 `x.compareTo(y) == 0` 意味着 `sgn(x.compareTo(z)) == sgn(y.compareTo(z))`。
- 强烈推荐 `(x.compareTo(y) == 0) == (x.equals(y))`，但不是必需的。 一般来说，任何实现了 `Comparable` 接口的类违反了这个条件都应该清楚地说明这个事实。 推荐的语言是「注意：这个类有一个自然顺序，与 `equals` 不一致」。

我们重点来看最后一条，虽然不强制，但当我们违反时，我们的程序有可能发生一些错误，如果违反，顺序关系被认为与 `equals` 不一致。 其 `compareTo` 方法施加与 `equals` 不一致顺序关系的类仍然有效，但包含该类元素的有序集合可能不服从相应集合接口（Collection，Set 或 Map）的一般约定。 这是因为这些接口的通用约定是用 `equals` 方法定义的，但是排序后的集合使用 `compareTo` 强加的相等性测试来代替 `equals`。 比如：

　　考虑 `BigDecimal` 类，其 `compareTo` 方法与 `equals` 不一致。 如果你创建一个空的`HashSet`实例，然后添加 `new BigDecimal("1.0")` 和 `new BigDecimal("1.00")`，则该集合将包含两个元素，因为与 `equals` 方法进行比较时，添加到集合的两个 `BigDecimal` 实例是不相等的。 但是，如果使用 `TreeSet` 而不是 `HashSet` 执行相同的过程，则该集合将只包含一个元素，因为使用 `compareTo` 方法进行比较时，两个`BigDecimal`实例是相等的。

**如何比较对象？**

① 实现`Comparable`

~~~java
public class Person implements Comparable<Person> {
    String name;
    int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }
    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.id,o.id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
~~~

② 实现`Comparator`

~~~java
public class Person2 implements Comparator<Person2> {
    String name;
    int id;
    public Person2(String name, int id) {
        this.name = name;
        this.id = id;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int compare(Person2 o1, Person2 o2) {
        return o1.name.compareTo(o2.name);
    }
}
~~~

③ 使用`lambda`表达式

~~~java
public class Test {
    public static void main(String[] args) {
        Person p1=new Person("a",1);
        Person p2=new Person("b",4);
        Person p3=new Person("c",0);
        Person[] persons=new Person[]{p3,p1,p2};    
        Arrays.sort(persons, (o1, o2) -> o1.name.compareTo(o2.name));
    }
}
~~~

需要注意的是，在比较时，不用使用减法，如：

~~~java
o1.hashCode() - o2.hashCode();
~~~

它可能会导致整数最大长度溢出。推荐使用静态 `compare` 方法：

~~~java
Integer.compare(o1.hashCode(), o2.hashCode());
~~~

对于字符串，则使用：

~~~java
o1.name.compareTo(o2.name);
~~~

# 三、类和接口

## 3.1 使类和成员的可访问性最小化

一个组件设计的好坏，**唯一重要的因素**在于，它对于外部的组件而言，**是否隐藏了其内部数据和其他实现细节**。这有利于组件间的解耦，方便各个组件独立的开发、测试、优化和使用。

虽然信息隐藏本身并不会使组件拥有更好的性能，但它可以让我们更有效的调节性能。

### 3.1.1 访问控制符

Java 提供了许多机制来帮助信息隐藏。 其中，访问控制机制指定了类，接口和成员的可访问性。 实体的可访问性取决于其声明的位置，以及声明中存在哪些访问修饰符。 正确使用这些修饰符对信息隐藏至关重要。

- private —— 该成员只能在声明它的顶级类内访问。
- package-private —— 成员可以从被声明的包中的任何类中访问。从技术上讲，如果没有指定访问修饰符，就默认为这个级别。
- protected —— 成员可以从被声明的类的子类中访问，以及它声明的包中的任何类。
- public —— 该成员可以从任何地方被访问。

### 3.1.2 该使用什么访问控制符

原则：**让每个类或成员尽可能地不可访问。** 换句话说，使用尽可能低的访问级别。

#### ① 顶层类和接口，尽量使用包级私有

对于顶层（非嵌套的）类和接口，只有两个可能的访问级别：包级私有（package-private）和公共的（public）。如果你使用 public 修饰符声明顶级类或接口，那么它是公开的；否则，它是包级私有的。通过将其设置为包级私有，**可以将其作为实现的一部分，而不是导出的 API，你可以修改它、替换它，或者在后续版本中消除它，而不必担心损害现有的客户端。如果你把它公开，你就有义务永远地支持它，以保持兼容性。**

#### ② 考虑私有静态嵌套类

**如果一个包级私有顶级类或接口只被一个类使用，那么可以考虑将其作为私有静态嵌套类** 。这将它的可访问性从包级的所有类减少到使用它的一个类。但是，减少不必要的公共类的可访问性要比包级私有的顶级类更重要：公共类是包的 API 的一部分，而包级私有的顶级类已经是这个包实现的一部分了。

#### ③ 什么时候将private改为default

 只有当同一个包中的其他类真的需要访问一个成员时，才应该删除私有修饰符，从而使该成员成为包级私有的。 如果你发现自己经常这样做，你应该重新检查你的系统的设计

#### ④ 公共类的实例域绝不能是公有的

如果一个实例字段是非 final 的，或者是对可变对象的引用（比如下面的代码），那么如果将其公开，就相当于你放弃了对该字段的值进行限制的能力。另外，当字段被修改时，你也没有对其采取任何行动的能力。**因此带有公共可变字段的类通常不是线程安全的** 。

~~~java
public class Thing {
    public static final String[] VALUES={"1","2","3"};
}
public class Test {
    public static void main(String[] args) {
        Thing.VALUES[0]="4";
        System.out.println(Thing.VALUES[0]);
    }
}
~~~

对于这种情况，我们通常有两种解决方法：

~~~java
public class Thing {
    // 增加一个公有的不可变列表
    private static final String[] PRE_VALUES={"1","2","3"};
    public static final List<String> VALUES2= Collections.unmodifiableList(Arrays.asList(PRE_VALUES));
}
public class Test {
    public static void main(String[] args) {
        Thing.VALUES2[0]="4"; // 报错
    }
}
~~~

~~~java
public class Thing {
    private static final String[] PRE_VALUES={"1","2","3"};
    public static final String[] values(){
        return PRE_VALUES.clone();
    }
}
public class Test {
    public static void main(String[] args) {
        // 此处为了方便测试，将PRE_VALUES改为public
        String[] values = Thing.values();
        values[0]="4";
        System.out.println(Thing.PRE_VALUES[0]); // 1
    }
}
~~~

同样的建议适用于静态字段，但有一个例外。 假设常量是类的抽象的一个组成部分，你可以通过 public static final 字段暴露常量。 按照惯例，这些字段的名字由大写字母组成，字母用下划线分隔。 很重要的一点是，这些字段包含基本类型的值或对不可变对象的引用。 

## 3.2 要在公有类中使用访问方法而非公有域

有时候，可能需要编写一些退化类，它们没有什么作用，只是用来集中实例域 ：

~~~java
class Point {
    public double x;
    public double y;
}
~~~

由于这种类的数据域是可以被直接访问的，这些类没有提供封装的功能。 如果不改变 API，就无法改变它的数据表示法， 也无法强加任何约束条件；当域被访问的时候，无法采取任何辅助的行动 。 应该用包含私有域和公有访问方法（ getter ） 的类代替 。 对于可变的类来说，应该用公有设值方法（ setter ）的类代替 ：

~~~java
class Point {
    private double x;
    private double y;
    pub1ic Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = X; }
    public void setY(double y) { this.y = y; }
}
~~~

如果类可以在它所在的包之外进行访问，就提供访问方法，以保留将来改变该类的内部表示法的灵活性 。如果公有类暴露了它的数据域 ，要想在将来改变其内部表示法是不可能的，因为公有类的客户端代码已经遍布各处了 。

然而 ， **如果类是包级私有的，或者是私有的嵌套类， 直接暴露它的数据域并没有本质的错误**——假设这些数据域确实描述了该类所提供的抽象 。 无论是在类定义中，还是在使用该类的客户端代码中，这种方法比访问方法的做法更不容易产生视觉混乱 。 虽然客户端代码与该类的内部表示法紧密相连，但是这些代码被限定在包含该类的包中 。 如有必要，也可以不改变包之外的任何代码，而只改变内部数据表示法 。 在私有嵌套类的情况下，改变的作用范围被进一步限制在外围类中 。

让公有类直接暴露域虽然从来都不是种好办法，但是如果域是不可变的，这种做法的危害就比较小一些 。 如果不改变类的 API ，就无法改变这种类的表示法，当域被读取的时候，你也无法采取辅助的行动，但是**可以强加约束条件** 。 例如，这个类确保了每个实例都表示一个有效的时间：

```java
public final class Time {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;
    public final int hour;
    public final int minute;

    public Time(int hour, int minute) {
        if (hour < 0 || hour >= HOURS_PER_DAY)
            throw new IllegalArgumentException("Hour: " + hour);
        if (minute < 0 || minute >= MINUTES_PER_HOUR)
            throw new IllegalArgumentException("Min:"+ minute);
        this.hour = hour;
        this.minute = minute;
    }
    ... // Remainder omitted
}
```

## 3.3 使可变性最小化

不可变类从其开始被创建，至其生命周期结束，其状态都是不可变的。`Java`类库中也提供了许多不可变类，如`String`、`BigInteger`、`BigDecimal`等。

### 创建不可变类

为了创建不可变类，可遵循以下5条规则：

1. 为类的域不提供修改对象状态的方法，通常就是`setter`方法；

2. 类不可被继承：类不可被继承有两种实现方式。一为类用`final`修饰，表示不可被继承；二为类不提供公有构造方法，采用私有构造方法，如果需要构造类则提供静态工厂方法。
3. 类的域都声明为`final`
4. 类的域都声明为`private`
5. 对类的任何可变组件确保是互斥访问：如果类有任何域涉及到可变对象，务必要确保类的调用方不能获取到该可变对象的引用

针对规则1、3和4，可能比较苛刻，在适当的时候可以考虑放松，譬如一些不可变类可能需要包含一个或多个非`final`的属性，用来缓存在计算中浪费性能的数据，从而节约开销。

构建一个不可变类实例：

~~~java
public final class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // 只提供getter，不提供setter
    public double realPart() { return re; }
    public double imaginaryPart() { return im; }

    //加
    public Complex add(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    //减
    public Complex subtract(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    //乘
    public Complex multiply(Complex c) {
        return new Complex(re * c.re - im * c.im,
            re * c.im + im * c.re);
    }

    //除
    public Complex divide(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp,
            (im * c.re - re * c.im) / tmp);
    }

    @Override public boolean equals(Object o) {
        if (o == this)
            return true;
            if (!(o instanceof Complex))
                return false;

            Complex c = (Complex) o;
            return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
    }

    @Override public int hashCode() {
        int result = 17 + hashDouble(re);
        result = 31 * result + hashDouble(im);
        return result;
    }

    private int hashDouble(double val) {
        long longBits = Double.doubleToLongBits(re);
        return (int) (longBits ^ (longBits >>> 32));
    }

    @Override public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}
~~~

从上面代码中的加、减、乘、除方法可以看出，结果返回的都是重新构建的新对象，而不是对原对象的修改。这种方式通常被称为函数方式，相比于过程和命令的方式，就是不改变对象的状态。

**不可变类优缺点**

**优点**

- 不可变类简单

- 不可变类从被创建，其状态都不会被改变

- 不可变类是线程安全的，不需要synchronized

- 不可变类可以被自由共享使用

- 不可变类中对于经常使用的类可以先创建缓存起来，之后可以直接调用。参见Boolean中的TRUE、FALSE。

- 不可变类不仅可以共享其实例，还可以共享其内部。参见BigInteger的negate方法。


- 不可变对象为其他可变或不可变对象提供大量的构件。如果你知道一个复杂对象内部的组件对象是不可变的，那么维护他的约束关系就更容易。这条原则的一个例子是，不可变对象构成了大量的map key和set元素，一旦不可变对象进入map或set中，你就不必担心他们的值变化导致破坏map和set的约束关系。

**缺点**

不可变的缺点是为了区分值的不同，都需要创建一个对象。这涉及到资源的问题。特别是如果不可变类是在循环中被创建的，会导致产生大量的临时不可变对象。

针对上面的问题解决方法有两种：

- 判断哪些步骤是多次循环创建对象，其不可变对象的创建可否采用基本类型代替。如BigInteger用int；
- 如果不能用基本类型代替，则看是否有可变配套类提供。常见的是String的可变配套类为StringBuilder

## 3.4 复合优先于继承

面向对象编程中，有一条非常经典的设计原则，那就是：组合优于继承，多用组合少用继承。

当然，继承分两种，实现继承和接口继承，此处讲的是**实现继承**。

### 3.4.1 在实际开发中继承的缺点

与方法调用不同，继承打破了封装性:

子类依赖于其超类中的特定功能的实现细节，如果需要覆盖超类的方法，就必须要知道超类所有的方法的内部逻辑，否则会照成意想不到的事故 举个例子:

~~~java
public class MyHashSet<E> extends HashSet<E> {
    private int addCount=0;
    public MyHashSet(){};
    public MyHashSet(int initCap,float loadFactor){
        super(initCap,loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount+=c.size();
        return super.addAll(c);
    }
    public int getAddCount(){
        return addCount;
    }
}
public class Test {
    public static void main(String[] args) {
        MyHashSet<String> myHashSet=new MyHashSet();
        myHashSet.addAll(List.of("a","b","c"));
        System.out.println(myHashSet.getAddCount()); // 6
    }
}
~~~

在上面的代码中，我们实现了一个新的`HashSet`，希望能记录自它被创建以来共添加了多少个元素。而`HashSet`中添加的方法有两个：`add()`，`addAll()`，我们需要重写这两个方法。理论上来说最终打印的结果应该是3，但实际结果确为6，问题出在哪？

我们通过查看`addAll()`源码可以看出，它自身调用了`add()`方法，这就导致重复计数了。实际上这是类中方法的”自用性“导致的，但我们并不一定知道其中的实现细节，所以盲目的继承可能就会导致一定的问题。

~~~java
public boolean addAll(Collection<? extends E> c) {
    boolean modified = false;
    for (E e : c)
        if (add(e))
            modified = true;
    return modified;
}
~~~

### 3.4.2 复合/转发

有一种方法可以避免以上的所有问题。不用扩展现有的类，而是在新的类中增加一个私有对象，它引用现有类的一个实例，这种设计被称作为复合，因为现有的类变成了新类的一个组件，新类中的每个实例方法都可以调用被包含的现有类实例中对应的方法，并返回它的结果，这称为转发，新类中的方法被称为转发方法。

~~~java
public class MyHashSet2<E> extends ForwardingSet<E>{
    private int addCount=0;
    public MyHashSet2(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount+=c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}

public class ForwardingSet<E> implements Set<E> {
    private final Set<E> s;

    public ForwardingSet(Set<E> s) {
        this.s = s;
    }

    @Override
    public int size() {
        return s.size();
    }

    @Override
    public boolean isEmpty() {
        return s.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return s.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return s.iterator();
    }

    @Override
    public Object[] toArray() {
        return s.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return s.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return s.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return s.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return s.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return s.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return s.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return s.removeAll(c);
    }

    @Override
    public void clear() {
        s.clear();
    }
}

public class Test {
    public static void main(String[] args) {
        MyHashSet2<String> myHashSet2=new MyHashSet2<>(new HashSet<>());
        myHashSet2.addAll(List.of("a","b","c"));
        System.out.println(myHashSet2.getAddCount()); // 3
    }
}
~~~

当你使用组合/转发模式时，`MyHashSet2` 类不是直接继承 `HashSet`，而是通过 `ForwardingSet`一个 `Set` 实例并转发调用。这意味着，尽管底层的 `Set`（例如 `HashSet`）的 `addAll` 实现可能会调用它自己的 `add` 方法来添加每个元素，但这些调用不会直接触发 `MyHashSet2` 中覆盖的 `add` 方法，因为它们是在底层 `Set` 的上下文中，而不是 `MyHashSet2` 的上下文中进行的。

换句话说，当你在 `MyHashSet2` 类中覆盖 `addAll` 方法时，如果你直接调用 `super.addAll(c)`，实际上调用的是 `ForwardingSet` 中转发的 `addAll` 方法，该方法又会调用底层 `Set` 的 `addAll` 方法。此时，`MyHashSet2` 的 `add` 方法不会因为 `HashSet` 的 `addAll` 方法内部可能进行的 `add` 调用而被间接触发。因此，`MyHashSet2` 中的 `addCount` 增加的控制变得更加明确和直接，不会因为 `addAll` 方法内部的 `add` 调用而受到影响。

实际上这种思想便是装饰者模式。

### 3.4.3 复合相比较于继承的优点和缺点

1、优点

通过在新类增加一个私有域，引用原本的超类(后面同意叫需要叫现有类)，使现有类变成新类的一个组件，而新类的方法都可以调用现有类里面的对应的方法，这个也叫转发
没有打破封装，就算现有类添加新的方法，或者修改原来方法的逻辑(方法入参和返回结果不能有改变)，也不会影响到新类

2、缺点：

不适合回调框架，因为回调框架是把对象自身的引用传递给其他的对象，用于后续的调用，但是包装起来的对象并不知道它外面的对象，所以它传递一个执行自身的引用，回调时避开了外面的包装对象，这也被称为SELF问题

### 3.4.4 何时使用继承，何时使用复合？

1、只有当子类真正是超类的子类型时，也就是说，对于两个类A和B，只有当两者之间确实存在"is-a"关系的时候才使用继承，否则使用复合。

2、如果子类只需要实现超类的部分行为，则考虑使用复合。

3、如果你试图扩展的类它的API有缺陷，继承机制会把缺陷传递给子类，而复合则允许设计新的API来隐藏这些缺陷。

## 3.5 要么设计继承并提供说明文档，要么禁止继承

文档必须精确地描述覆盖每个方法所带来的影响。该类必须有文档说明它可覆盖的方法的自用性。对于每个公有的或受保护的方法或者构造器，它的文档必须指明该方法或者构造器调用了哪些可覆盖的方法，是以什么顺序调用的，每个调用的结果又是如何影响后续的处理过程的。更一般的，类必须在文档中说明，在哪些情况下它会调用可覆盖的方法。

按惯例，如果方法调用到了可覆盖的方法，在它的文档注释的末尾应该包含关于这些调用的描述信息。这段描述信息要以这样的句子开头：“**This implementation...**”。这样的句子不应该被认为是在表明该行为可能会随着版本的变迁而改变。它意味着这段描述关注该方法的内部工作情况

~~~java
public boolean remove(Object o)  
  Removes a single instance of the specified element from this collection, if it is present (optional operation). More formally, removes  
 an element e such that (o==null ? e==null : o.equals(e)), if this collection contains one or more such elements. Returns true if this   
collection contained the specified element (or equivalently, if this collection changed as a result of the call).  
  This implementation iterates over the collection looking for the specified element. If it finds the element, it removes the element   from the collection using the iterator's remove method.  

（如果这个集合中存在制定的元素，就从中删除该指定元素中的单个实例（这是项可选的操作）。更一般地，如果集合中包含一个或者多个这样的元素e，就从中删除这种元素，以便（o == null ？ e==null：o.equals(e)）。如果集合中包含制定的元素，就返回true（如果调用最终改变了集合，也一样）

该实现遍历整个集合来查找制定的元素。如果它找到该元素，将会利用迭代器的remove方法将之从集合中删除。注意，如果由该集合的iterator方法返回的迭代器没有实现remove方法，该实现就会抛出UnsupportedOperationException。）
~~~

该文档清楚地说明了，覆盖iterator方法将会影响remove方法的行为。而且，它确定地描述了iterator方法返回的Iterator的行为将会怎样影响remove方法的行为。

**与此相反的是，对于HashSet，并无说明覆盖add方法是否会影响addAll方法的行为。**

对于程序文档的格言：好的API文档应该描述一个给定的方法做了什么工作，而不是描述它是如何做到的。由此看来，上面的这段文档违背了这一格言，这正是继承破坏了封装性所带来的不幸后果，因为在上面这段文档中它必须要说明清楚调用可覆盖方法所带来的影响。所以，为了设计一个类的文档，以便它能够被安全的子类化，必须描述清楚那些有可能未定义的实现细节。

## 3.6 接口优于抽象类

### 3.6.1 接口和抽象类

Java中抽象类和接口的区别

### 3.6.2 接口优点

1、现有的类可以很容易的被更新，以实现新的接口。

如果你前期编写了一个类A，后期有在系统中加入了一个新的接口B，当你想让前期编写的类来实现这个接口，你只用加上一句implement B，然后在类A中实现里面的方法即可，不会影响到以前的类对类A的使用。

但是抽象类就不一样了，因为他用的是继承，如果你让A继承一个抽象类，会导致A的子类也跟着间接继承了这个抽象类。

2、接口是定义mixin（混合类型）的理想选择。

一个类可以实现多个接口。一个类除了可以实现一个它的主要类型接口之外，还可以加入一些辅助接口来实现一些新的功能。

3、接口允许我们构造非层次结构的类型框架。

其实就是说，接口可以多实现。不用像继承一样层次分明。

例：假设我们有一个接口代表一个singer，另一个接口代表一个songweiter。

~~~java
public interface Singer{
 AudioClip sing(Song s);
}
public interface Songwriter{
 Song compose(boolean hit);
}
~~~

在现实生活中，有些歌唱家本身也是作曲家。因为我们使用了接口而不是抽象类来定义这些类型，所以对于单个类而言，它同时实现Singer和Songwriter是允许的，实际上我们可以定义第三个接口，他同时扩展了Singer和Songwriter，并添加了一些适合于这种组合的新方法：

~~~java
public interface SingerSongwriter extends Singer, Songwriter{
 AudioClip strum();
 void actSensitive();
}
~~~

你并不是总是需要这种灵活性，但是一旦你这样做了，接口能帮助你解决大问题。

另外一种做法是编写一个臃肿的类层次，对于每一种要被支持的属性组合，都包含一个单独的类。如果在整个类型系统中有n个属性，那么必须支持2^n种可能的组合。这种现象被称为“组合爆炸”。类层次的臃肿也导致类也臃肿，这些类也包含许多方法，并且这些方法只是在参数的类型上有所不同而已，因为类层次中没有任何类型体现公共的行为特征。

4、接口可以使得类的增强变得安全。

比如3.4中为HashSet添加计数功能的例子

### 3.6.3 骨架类

java 8之前接口是不可以有方法体的，这就是抽象类相对于接口的优势，为了将抽象类和接口的优势整合起来，“骨架类”就诞生了，骨架类的做法是用一个抽象类来实现一个接口，在抽象类中为接口的某些方法提供实现。

骨架类的实现的一般步骤是:

1.找出接口中的基本方法
2.在抽象类中声明为抽象方法
3.然后用这些基本方法来实现其他方法，所谓基本方法，就是通过将这些方法组合或是变换，可以实现其他的方法。

编写骨架实现类：

首先，必须认真研究接口，并确定哪些方法时最为基本的，其他方法则可以根据它们来实现。这些方法将成为骨架实现类中的抽象方法。
然后，必须为接口中的所有其他的方法提供具体实现。比如：

~~~java
// 求和接口
public interface Summation<T>{
    // 实现两个对象的相加
    T towEleAdd(T obj01, T obj02);

    // 实现List求和
    T listEleSum(List<T> list);

    // 实现数组求和
    T arrayEleSum(T[] array);
}
// 骨架类
public abstract class AbstractSummation<T> implements Summation<T>{
    @Override
    public abstract T towEleAdd(T obj01, T obj02);
    @Override
    public T listEleSum(List<T> list) {
        T firstEle = null;
        for (T t : list) {
            if (firstEle == null) {
                firstEle = t;
                continue;
            }
            firstEle = towEleAdd(firstEle, t);
        }
        return firstEle;
    }
    @Override
    public T arrayEleSum(T[] array) {
        T firstEle = null;
        for (T t : array) {
            if (firstEle == null) {
                firstEle = t;
                continue;
            }
            firstEle = towEleAdd(firstEle, t);
        }
        return firstEle;
    }
}
// 客户端
public class Client {
    public static void main(String[] args) {
        MySummation mySummation=new MySummation();
        Object[] nums= new Object[]{1,2,3};
        System.out.println(mySummation.arrayEleSum(nums));
    }
}
~~~

但是在java8中，接口可以有默认方法了，所以也可以在接口中实现：

~~~java
public interface Summation2<T> {
    // 实现两个对象的相加
    T towEleAdd(T obj01, T obj02);

    // 实现List求和
    T listEleSum(List<T> list);
    // 实现数组求和

    default T arrayEleSum(T[] array) {
        T firstEle = null;
        for (T t : array) {
            if (firstEle == null) {
                firstEle = t;
                continue;
            }
            firstEle = towEleAdd(firstEle, t);
        }
        return firstEle;
    }
}
~~~

## 3.7 为后代设计接口

 在Java 8 发行之前，如果不破坏现有的实现，是不可能给接口添加方法的。如果给某个接口添加了一个新的方法，一般来说，现有的实现中是没有这个方法的，因此就会导致编译错误。在Java 8 中，增加了缺省方法，目的就是允许给现有的接口添加方法。但是给现有接口添加新方法还是充满风险的。

缺省方法的声明中包括一个缺省实现（default implementation），这是给实现了该接口但没有实现默认方法的所有类使用的。虽然Java 中增加了缺省方法之后，可以给现有接口添加方法了，但是并不能确保这些方法在之前存在的实现中都能良好运行。因为这些默认的方法是被“注入”到现有实现的，它们的实现者并不知道，也没有许可。在Java 8之前，编写这些实现时，是默认它们的接口永远不需要任何新方法的。

Java 8 在核心集合接口中增加了许多新的缺省方法，主要是为了便于使用 lambda。Java 类库的缺省方法是高品质的通用实现，它们在大多数情况下都能正常使用。但是，并非每一个可能的实现的所有变体，始终都可以编写出一个缺省方法。

这种情况一方面方便了扩展工作，但同时也不可避免的带来一些副作用。接口的default 方法便是如此，对于一个接口的扩展工作而言，添加新的方法，为了避免对实现类的破坏性更新，很多时候需要使用default 方法来避免实现类编译报错，不过有些时候就会造成一些问题。有时候问题比较小，就是default 方法对于某些具体实现可能不起作用。而有些时候问题可能严重，会造成某些具体实现类的功能被破坏。

比如下面的例子：

~~~java
// 用户接口
public interface IUser {
    void setName(String name);
    void setPassword(String password);
    String getName();
    String getPassword();
    default void setPasswordIf(String password, Predicate<String> filter){
        if(filter.test(password)){
            setPassword(password);
        }
    }
}
// 用户类的普通实现
public class User implements IUser{
    private String name;

    private String password;
    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public void setPassword(String password) {
        this.password=password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
// 用户类的特殊实现，这里要求对密码进行二次确认。
public class MyUser implements IUser {
    private String name;

    private String password;

    private String tmp;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        if (password != null)
            this.tmp = password;
    }

    public void confirmPassword(String password) {
        if (Objects.equals(tmp, password)) {
            this.password = password;
            this.tmp = null;
        }
    }
}
// 测试
public class Test {
    public static void main(String[] args) {
        User user=new User();
        user.setPasswordIf("b", s -> !s.isEmpty());
        System.out.println(user.getPassword()); // b

        MyUser myUser=new MyUser();
        myUser.setPasswordIf("c",s -> !s.isEmpty());
        System.out.println(myUser.getPassword()); // null
    }
}

~~~

其原因归根到底，在于“接口不知道自己有多少实现类” 。 最终导致的结果就是，在添加 default 方法的时候，接口不知道实现类究竟有几个，是怎么实现的。只能按照最普遍，最一般的写法来实现。 这样的话，对于常规的类是没有问题的。但对于“特殊定制”，比如上面的要求二次确认等，这一类不太常见的需求的时候， 接口的 default 方法非常容易无效化，或者造成破坏性后果。

所以，在设计接口的时候，尽量灵活一些，能够适当考虑以后的变化。不要指望已经开始实现之后，再去添加 default 方法来满足新的需求，这种情况很容易照顾不到一些特殊的类。

**最后，补充说明，以上的情况其实说的都是公开的程序，会暴露API给其他程序的这种开放性的程序。对于纯内部的项目，不会有其他人，其他程序 来对接的项目，这些都不是问题，因为API 的影响范围只在内部，无非是上下游，前后端一起修改的事情。这种情况不需要多说什么，怎么开发都行。**

## 3.8 接口只用于定于类型

当类实现接口时，接口就充当可以引用这个类的实例的类型。因此，类实现了接口，就表明可以对这个类的实例实施某些动作。那么，不是为了这个目的而定义接口就是不恰当的。比如常量接口，这种接口没有包含任何方法，它只包含静态的final域，每个域都导出一个常量。

~~~java
public interface PhysicalConstants {
    static final double AVOGADROS_NUMBER = 6.022141;
    static final double BOLTZMANN_CONSTANT = 1.12588456e-23;
    static final double ELECTRON_MASS = 9.10938188e-31;
}
~~~

常量接口模式是对接口的不良使用。 类在内部使用某些常量，这纯粹是实现细节。

- 实现常量接口，会导致把这样的实现细节泄露到该类的导出API中。

- 更糟糕的是，他代表了一种承诺：如果将来的发行版本中，这个类被修改了，他不再需要使用这些常量了，他依然必须实现这个接口，以确保二进制兼容性。
- 如果非final类实现了常亮接口，他的所有子类的命名空间也会被接口中的常量所“污染”。

**如果要导出常量，可以有几种合理的选择方案。**

① 如果这些常量与某个现有的类或者接口紧密相关，就应该把这些常量添加到这个类或者接口中。例如，在java平台类库中所有的数值包装类，比如Integer和Double，都导出了MIN_VALUE和MAX_VALUE常量。

~~~java
public final class Integer extends Number implements Comparable<Integer> {
    /**
     * A constant holding the minimum value an <code>int</code> can
     * have, -2<sup>31</sup>.
     */
    public static final int   MIN_VALUE = 0x80000000;

    /**
     * A constant holding the maximum value an <code>int</code> can
     * have, 2<sup>31</sup>-1.
     */
    public static final int   MAX_VALUE = 0x7fffffff;
}
~~~

② 如果这些常量最好被看做枚举类型的成员，就应该用枚举类型来导出这些常量。

③ 使用不可实例化的工具类（utility class）来导出这些常量

~~~java
public class PhysicalConstants {

    private PhysicalConstants(){}
    //阿伏伽德罗数
    public static final double AVOGADROS_NUMBER = 6.02214199e23;

    //玻尔兹曼常数
    public static final double BOLRZMANN_CONSTANT = 1.3806503E-23;

    //电子质量
    public static final double ELECTRON_MASS = 9.10938188E-31;
}
~~~

工具类通常要求客户端要用类名来修饰这些常量名，例如PhysicalConstants.AVOGADROS_NUMBER。如果大量利用工具类导出的常量，可以通过利用静态导入（static import）机制。避免用类名来修饰常量名，不过静态导入机制是在java发行版本1.5中才引入的：

~~~java
import static effective_java.chapter4_classes_and_interfaces.item22.PhysicalConstants.AVOGADROS_NUMBER;

public class Test {
    public static void main(String[] args) {
        System.out.println(AVOGADROS_NUMBER * 10);
    }
}
~~~

## 3.9 类层次优先于标签类

有时候，可能会遇到带有两种甚至多种风格的实例的类，并包含表示实例风格的标签（tag）域，例如，考虑下面这个类，它能够表示圆形或者矩形：

~~~java
public class Figure {
    enum Shape{RECTANGLE,CIRCLE};
    final Shape shape;
    double length;
    double width;
    double radius;
    Figure(double radius){
        shape=Shape.CIRCLE;
        this.radius=radius;
    }
    Figure(double length,double width){
        shape=Shape.RECTANGLE;
        this.length=length;
        this.width=width;
    }
    double area(){
        switch (shape){
            case CIRCLE:
                return Math.PI*(radius*radius);
            case RECTANGLE:
                return length*width;
            default:
                throw new AssertionError(shape);
        }
    }
}
~~~

这种标签类（tagged class）有着许多缺点。他们中充斥着样板代码，包括枚举声明，标签域以及条件语句。由于多个实现乱七八糟地挤在了单个类中，破坏了可读性。内存占用增加了，因为实例承担着属于其他风格的不相关的域。域不能做成是final的，除非构造器初始化了不相关的域，产生更多的样板代码。构造器必须不借助编译器，来设置标签域，并初始化正确的数据域：如果初始化了错误的域，程序就会在运行时失败。无法给标签类添加风格，除非可以修改它的源文件。如果一定要添加风格，就必须记得给每个条件语句都添加一个条件，否则类就会在运行时失败。最后，实例的数据类型没有提供任何关于其风格的线索，**一句话，标签类过于冗长，容易出错，效率低下，且极度不灵活，很多地方都写死了。**

幸运的是，面向对象的语言例如Java，就提供了其他更好地方法来定义能表示多种风格对象的单个数据类型：子类型化（subtyping）。标签类正是类层次的一种简单的仿效。

~~~java
abstract class Figure {
    abstract double area();
}

public class Circle extends Figure{
    final double radius;
    Circle(double radius){
        this.radius=radius;
    }
    @Override
    double area() {
        return Math.PI*(radius*radius);
    }
}

public class Rectangle extends Figure{
    final double length;
    final double width;
    Rectangle(double length,double width){
        this.length=length;
        this.width=width;
    }
    @Override
    double area() {
        return length*width;
    }
}

~~~

这个类层次纠正了前面提到过的标签类的所有缺点。这段代码简单且清楚，没有包含在原来的版本中所见到的所有样板代码。每个类型的实现都配有自己的类，这些类都没有受到不相关的数据域的拖累。所有的域都是final的。编译器确保每个类的构造器都初始化它的数据域，对于根类中声明的每个抽象方法，都确保有一个实现。这样就杜绝了由于遗漏switch case 而导致运行时失败的可能性。多个程序员可以独立的扩展层次结构，并且不用访问根类的源代码就能相互操作。每种类型都有一种相关的独立的数据类型，允许程序员指明变量的类型，限制变量，并将参数输入到特殊的类型。

类层次的另一种好处在于，他们可以用来反映类型之间本质上的层次关系，有助于增强灵活性，并进行更好地编译时类型检查。

## 3.10 静态成员类优于非静态成员类

静态成员类：相比于其他类，它被声明在了一个类内部，能够访问外部类的静态方法及静态变量。私有的静态成员类只在外部类内可访问。
非静态成员类：每个实例都必须与一个外部类实例相关联，可以调用任何外部类实例的方法，可以获取到外部类实例的引用。

一、静态成员类相较于非静态成员类的优势：
1.**不依赖外部类的实例，可以单独存在。**（内部类不要求访问外部实例，一定要加static）。
2.由于没有和外部类实例关联，可以**减少时间空间的开销**。非静态内部类由于有关联，会多保存一份指向外部类的引用，导致消耗时间空间的同时。
3.静态成员类**没有内存泄漏风险**。外部类实例由于存在内部类实例引用，符合垃圾回收却仍会被保留，不处理会导致内存泄漏。
嵌套类需要在多个方法内可见，或类太长了（考虑可读性），使用成员类。当内部类需要外部类实例引用，使用非静态类，否则都使用静态类。使用非静态成员类需要警惕内存泄漏问题。

二、匿名类：
1.使用时才会被声明和实例化，可以出现在任何允许存在表达式的地方。
2.只继承一个父类或实现一个接口
3.不可以有静态变量，只能获取外部作用域的常量
4.定义在非静态作用域内，会引用外部类实例
总的来说匿名类是当需要一个接口的实现或父类的子类实例（**只使用一次时**）（**足够简短**）可以使用匿名类。常见使用方法场景是静态工厂。
**创建时只有单一方法的接口可以用Lambda转换。**

**匿名内部类的本质：是一个继承类该类或者实现了该接口的子类匿名对象** 

三、局部类：**在方法体内定义的类。**
1.非静态环境下，能获取到外部类的实例，但不能访问当前方法的局部变量（final 修饰过除外）
2.保持简短
当一个类只需要一次使用时，可以做成局部类。有预置类型或行为时考虑匿名类。



非静态成员类的一个通常使用是定义一个Adapter，它允许外部类的实例被看成是某个不相关类的实例。例如，Map接口的实现通常使用非静态成员类实现他们的集合视图，它们是由Map’s keySet、entrySet和values返回。相似地，集合接口，比如Set和List，它们的实现通常使用非静态成员类来实现它们的迭代器：

```java
public class MySet<E> extends AbstractSet<E> { 
    ... // 这个类的大部分省略

    @Override public Iterator<E> iterator() { 
        return new MyIterator(); 
    }

    private class MyIterator implements Iterator<E> { 
        ...
    }
}
```

简要概括，有四种不同嵌套类，而且每种有它的位置。如果一个嵌套类需要在单个方法的外部可见，或者太长了而不适合在一个方法内部，那么使用成员类。如果成员类的每个实例需要它外部实例的引用，那么把它变成非静态；否则，把它变成静态。假设类属于一个方法的内部，如果你需要仅仅从一个位置创建实例，而且有描述这个类的特性的已存类，那么把它变成匿名类；否则，把它变成本地类。

## 3.11 限制源文件为单个顶级类

虽然Java编译器允许在单个源文件中定义多个顶级类，但这样做没有任何好处，并且存在重大风险。

风险源于在源文件中定义多个顶级类使得为类提供多个定义成为可能。

使用哪个定义会受到源文件传递给编译器的顺序的影响。

### 例子

为了具体说明，请考虑下面源文件，其中只包含一个引用其他两个顶级类（Utensil和Dessert类）的成员的Main类：

```java
public class Main {

    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }

}
```

现在假设在Utensil.java的源文件中同时定义了Utensil和Dessert：

```java
// Two classes defined in one file. Don't ever do this!
class Utensil {
    static final String NAME = "pan";
}

class Dessert {
    static final String NAME = "cake";

}
```

当然，main方法会打印pancake。

现在假设你不小心创建了另一个名为Dessert.java的源文件，它定义了相同的两个类：

```java
// Two classes defined in one file. Don't ever do this!
class Utensil {
    static final String NAME = "pot";
}

class Dessert {
    static final String NAME = "pie";
}
```

如果你足够幸运，使用命令javac Main.java Dessert.java编译程序，编译将失败，编译器会告诉你，你已经多次定义了类Utensil和Dessert。

这是因为编译器首先编译Main.java，当它看到对Utensil的引用（它在Dessert的引用之前）时，它将在Utensil.java中查找这个类并找到Utensil和Dessert。

当编译器在命令行上遇到Dessert.java时，它也将拉入该文件，导致它遇到Utensil和Dessert的定义。

如果使用命令javac Main.java或javac Main.java Utensil.java编译程序，它的行为与在编写Dessert.java文件（即打印pancake）之前的行为相同。 但是，如果使用命令javac Dessert.java Main.java编译程序，它将打印potpie。

程序的行为因此受到源文件传递给编译器的顺序的影响，这显然是不可接受的。

### 静态成员类

解决这个问题很简单，将顶层类（如我们的例子中的Utensil和Dessert）分割成单独的源文件。

如果试图将多个顶级类放入单个源文件中，请考虑使用静态成员类（条目 24）作为将类拆分为单独的源文件的替代方法。

如果这些类从属于另一个类，那么将它们变成静态成员类通常是更好的选择，因为它提高了可读性，并且可以通过声明它们为私有（条目 15）来减少类的可访问性。

下面是我们的例子看起来如何使用静态成员类：

```java
// Static member classes instead of multiple top-level classes
public class Test {

    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }

    private static class Utensil {
        static final String NAME = "pan";
    }

    private static class Dessert {

        static final String NAME = "cake";

    }

}
```

这个教训很清楚：**永远不要将多个顶级类或接口放在一个源文件中**。

遵循这个规则保证在编译时不能有多个定义。 这又保证了编译生成的类文件以及生成的程序的行为与源文件传递给编译器的顺序无关。

# 四、泛型

## 4.1 请不要使用原生态类型

所谓的原生态类型，就是不加泛型的集合类型，比如：

~~~java
List list=new ArrayList();
~~~

因为这样很可能让我们的错误代码逃过编译器的检查，导致运行时出现异常。

~~~java
public class Test {
    public static void main(String[] args) {
        List list=new ArrayList<>();
        list.add(0);
        list.add(1L);
        list.add(2D);
        list.add("3str");
        System.out.println(list);
    }
}
// [0, 1, 2.0, 3str]
~~~

因为`List`底层是一个`Object[]`，因此允许我们添加任意类型的元素。但是这也使得我们更容易出现错误。如果不小心往一个只存放`Integer`类型的集合中添加了一个其他类型，就容易导致运行时异常。

~~~java
public class Test2 {
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add("str");
        for (int i = 0; i < list.size(); i++) {
            Integer num=(Integer) list.get(i); // 类型转换失败
        }
    }
}
~~~

此外，要注意当我们使用泛型时，如果需要将带有泛型的集合进行传递，比如函数的参数等，对应的参数也需要声明泛型，否则会导致“泛型擦除”。这种情况下编译器不会报错，但是允许时依旧会出现异常。

~~~java
public class Test3 {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        add(list,1);
        add(list,"str");
        for (Integer i : list) {
            System.out.println(i);
        }
    }
    private static void add(List list,Object o){
        list.add(o);
    }
}
~~~

