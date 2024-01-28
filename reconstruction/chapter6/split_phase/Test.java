package reconstruction.chapter6.split_phase;

public class Test {
    public static void main(String[] args) {
        Product product=new Product(50.0,20.0,8);
        ShippingMethod shippingMethod=new ShippingMethod(10.0,5.0,2.0);
        SplitPhase splitPhase=new SplitPhase();
        double price1 = splitPhase.priceOrder(product, 10, shippingMethod);
        System.out.println(price1);
        System.out.println("-----------------------------------");
        AfterSplitPhase afterSplitPhase=new AfterSplitPhase();
        double price2 = afterSplitPhase.priceOrder(product, 10, shippingMethod);
        System.out.println(price2);
    }
}
