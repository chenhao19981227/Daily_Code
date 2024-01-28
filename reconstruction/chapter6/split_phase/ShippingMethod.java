package reconstruction.chapter6.split_phase;

public class ShippingMethod {
    double discountThreshold;
    double discountedFee;
    double feePerCase;

    public ShippingMethod(double discountThreshold, double discountedFee, double feePerCase) {
        this.discountThreshold = discountThreshold;
        this.discountedFee = discountedFee;
        this.feePerCase = feePerCase;
    }
}
