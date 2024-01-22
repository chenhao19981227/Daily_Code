package design_mode.singleton_mode;

public class Singleton2 {
    // 多线程场景下单例模式的实现
    private static Singleton2 singleton2;
    public static synchronized Singleton2 getInstance(){
        if(singleton2==null){
            singleton2=new Singleton2();
        }
        return singleton2;
    }
}
