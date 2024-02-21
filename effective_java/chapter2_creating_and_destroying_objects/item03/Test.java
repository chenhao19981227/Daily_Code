package effective_java.chapter2_creating_and_destroying_objects.item03;

public class Test {
    public static void main(String[] args) throws Exception {
        // 饿汉式
        System.out.println(Singleton1.getInstance()==Singleton1.getInstance());
        // 懒汉式
        System.out.println(Singleton2.getInstance()==Singleton2.getInstance());
        // 枚举类
        System.out.println(Singleton3.INSTANCE==Singleton3.INSTANCE);
    }
}
