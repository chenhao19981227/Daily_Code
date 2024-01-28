package reconstruction.chapter6.split_phase;

public class Product {
    double basePrice;
    double discountThreshold;
    double discountRate;

    public Product(double basePrice, double discountThreshold, double discountRate) {
        this.basePrice = basePrice;
        this.discountThreshold = discountThreshold;
        this.discountRate = discountRate;
    }
}
