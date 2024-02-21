package effective_java.chapter2_creating_and_destroying_objects.item03;

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
