package design_mode.singleton_mode;

public class Singleton2 {
    // 多线程场景下单例模式的实现
    private static Singleton1 singleton1;
    public static synchronized Singleton1 getInstance(){
        if(singleton1==null){
            singleton1=new Singleton1();
        }
        return singleton1;
    }
}
