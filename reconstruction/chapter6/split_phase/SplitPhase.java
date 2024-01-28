package reconstruction.chapter6.split_phase;

public class SplitPhase {
    public double priceOrder(Product product,int quantity,ShippingMethod shippingMethod){
        double basePrice=product.basePrice*quantity;
        double discount=Math.max(quantity-product.discountThreshold,0)*product.basePrice*product.discountRate;
        double shippingPerCase=(basePrice>shippingMethod.discountThreshold)?shippingMethod.discountedFee:shippingMethod.feePerCase;
        double shippingCost=quantity*shippingPerCase;
        double price=basePrice-discount+shippingCost;
        return price;
    }
}
