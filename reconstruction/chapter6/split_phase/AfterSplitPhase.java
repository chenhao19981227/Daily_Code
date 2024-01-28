package reconstruction.chapter6.split_phase;

public class AfterSplitPhase {
    public double priceOrder(Product product,int quantity,ShippingMethod shippingMethod){
        PricingData pricingData=calculatePricingData(product,quantity);
        return applyShipping(pricingData,shippingMethod);
    }
    public PricingData calculatePricingData(Product product,int quantity){
        double basePrice=product.basePrice*quantity;
        double discount=Math.max(quantity-product.discountThreshold,0)*product.basePrice*product.discountRate;
        return new PricingData(basePrice,discount,quantity);
    }
    public double applyShipping(PricingData pricingData,ShippingMethod shippingMethod){
        double shippingPerCase=(pricingData.basePrice>shippingMethod.discountThreshold)?shippingMethod.discountedFee:shippingMethod.feePerCase;
        double shippingCost=pricingData.quantity*shippingPerCase;
        return pricingData.basePrice-pricingData.discount+shippingCost;
    }
}
