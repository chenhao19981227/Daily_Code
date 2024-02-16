package reconstruction.chapter11.remove_flag_argument;

public class Test {
    public static void main(String[] args) {
        Order order=new Order("high");
        System.out.println(Before.deliveryPayment(order, true));
        System.out.println(Before.deliveryPayment(order,false));
        System.out.println("--------------------------------");
        System.out.println(After.rushDeliveryPayment(order));
        System.out.println(After.regularDeliveryPayment(order));
    }
}
