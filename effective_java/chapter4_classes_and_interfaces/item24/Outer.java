package effective_java.chapter4_classes_and_interfaces.item24;

public class Outer {
    static int m = 0; // 定义类的成员变量
    // 下面的代码定义了一个静态内部类
    static class Inner {
        int n = 1;
        void show () {
            // 在静态内部类的方法中访问外部类的成员变量
            System.out.println("外部静态变量m = " +m);
        }
    }
}
