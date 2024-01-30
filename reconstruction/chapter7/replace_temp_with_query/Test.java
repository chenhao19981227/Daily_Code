package reconstruction.chapter7.replace_temp_with_query;

public class Test {
    public static void main(String[] args) {
        Order order=new Order(10,10);
        System.out.println(order.prototypeGetPrice());
        System.out.println("----------------------");
        System.out.println(order.modificationGetPrice());
    }
}
