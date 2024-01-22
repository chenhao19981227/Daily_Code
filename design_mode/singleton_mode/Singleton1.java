package design_mode.singleton_mode;

public class Singleton1 {
    // 经典单例模式实现
    private static Singleton1 singleton1;
    public static Singleton1 getInstance(){
        if(singleton1==null){
            singleton1=new Singleton1();
        }
        return singleton1;
    }
}
