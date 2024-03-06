package design_mode.singleton_pattern.lazy;

public class Singleton2 {
    private Singleton2(){
        if(SingletonHolder.INSTANCE!=null){
            throw new RuntimeException("singleton");
        }
    };
    private static class SingletonHolder{
        private static final Singleton2 INSTANCE=new Singleton2();
    }
    public static Singleton2 getInstance(){
        return SingletonHolder.INSTANCE;
    }
    private Object readResolve() {
        return SingletonHolder.INSTANCE;
    }
}
