package reconstruction.chapter6.extract_variable;



public class ExtractVariable {
    public double price(Order order){
        return order.quantity*order.itemPrice-Math.max(0,order.quantity-500)*order.itemPrice*0.05+
                Math.min(order.quantity*order.itemPrice*0.1,100);
    }
}
