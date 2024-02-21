package effective_java.chapter2_creating_and_destroying_objects.item03;

public class Singleton1 {
    private Singleton1(){};
    private static final Singleton1 INSTANCE=new Singleton1();
    public static Singleton1 getInstance(){
        return INSTANCE;
    }
}
