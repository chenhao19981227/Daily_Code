package reconstruction.chapter11.remove_flag_argument;

public class Before {
    public static double deliveryPayment(Order order,boolean isRush){
        if(isRush){
            double payment=10;
            if(order.level.equals("high"))payment+=3;
            else if(order.level.equals("mid"))payment+=2;
            else payment+=1;
            return payment;
        }else {
            double payment=0;
            if(order.level.equals("high"))payment+=3;
            else if(order.level.equals("mid"))payment+=2;
            else payment+=1;
            return payment;
        }
    }
}
