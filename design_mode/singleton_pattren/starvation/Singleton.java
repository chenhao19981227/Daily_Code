package design_mode.singleton_pattren.starvation;

public class Singleton {
    private Singleton(){};
    private static Singleton INSTANCE=new Singleton();
    public static Singleton getInstance(){
        return INSTANCE;
    }
}
