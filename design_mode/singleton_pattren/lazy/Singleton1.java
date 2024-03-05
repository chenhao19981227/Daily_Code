package design_mode.singleton_pattren.lazy;


public class Singleton1 {
    private Singleton1(){};
    private static volatile Singleton1 INSTANCE;
    private static Singleton1 getInstance(){
        if(INSTANCE==null){
            synchronized (Singleton1.class){
                if(INSTANCE==null){
                    INSTANCE=new Singleton1();
                }
            }
        }
        return INSTANCE;
    }
}
