package reconstruction.chapter11.remove_flag_argument;

public class After {
    public static double regularDeliveryPayment(Order order) {
        double payment=0;
        if(order.level.equals("high"))payment+=3;
        else if(order.level.equals("mid"))payment+=2;
        else payment+=1;
        return payment;

    }
    public static double rushDeliveryPayment(Order order) {
        double payment=10;
        if(order.level.equals("high"))payment+=3;
        else if(order.level.equals("mid"))payment+=2;
        else payment+=1;
        return payment;
    }
}
