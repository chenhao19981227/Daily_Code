package effective_java.chapter4_classes_and_interfaces.item15;

public class Test {
    public static void main(String[] args) {
        Thing.VALUES[0]="4";
        System.out.println(Thing.VALUES[0]);

        String[] values = Thing.values();
        values[0]="4";
    }
}
