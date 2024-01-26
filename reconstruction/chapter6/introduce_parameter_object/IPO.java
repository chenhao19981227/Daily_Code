package reconstruction.chapter6.introduce_parameter_object;

public class IPO {
    public double price(double itemPrice,int quantity){
        return itemPrice*quantity;
    }
    // 改成参数对象
    public double price(Order order){
        return order.itemPrice*order.quantity;
    }

    public static void main(String[] args) {
        IPO ipo=new IPO();
        Order order=new Order(10.0,20);
        double price1 = ipo.price(10.0, 20);
        double price2 = ipo.price(order);
        System.out.println(price1);
        System.out.println("-----------------------");
        System.out.println(price2);
    }
}
