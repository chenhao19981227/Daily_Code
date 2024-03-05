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
