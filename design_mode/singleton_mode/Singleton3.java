package design_mode.singleton_mode;

public class Singleton3 {
    // 双重检查锁，防止重复判断同步锁
    private volatile static Singleton3 singleton3;
    public static Singleton3 getInstance(){
        if(singleton3==null){
            synchronized (Singleton3.class){
                if(singleton3==null){
                    singleton3=new Singleton3();
                }
            }
        }
        return singleton3;
    }
}
