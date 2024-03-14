package effective_java.chapter4_classes_and_interfaces.item17;

public class Test {
    public static void main(String[] args) {
        Complex complex = new Complex(1, 2);
        Complex plus = complex.plus(new Complex(2, 3));
        System.out.println(plus==complex);
    }
}
