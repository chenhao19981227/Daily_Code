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







