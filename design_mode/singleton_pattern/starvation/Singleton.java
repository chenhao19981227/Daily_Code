package design_mode.singleton_pattern.starvation;

public class Singleton {
    private Singleton(){};
    private static Singleton INSTANCE=new Singleton();
    public static Singleton getInstance(){
        return INSTANCE;
    }
}
