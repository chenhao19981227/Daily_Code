package reconstruction.chapter7.replace_temp_with_query;

public class Order {
    private int quantity;
    private double price;
    public Order(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    // 重构前
    public double prototypeGetPrice() {
        double basePrice = this.quantity * this.price;
        double discountFactor = 0.98;
        if (basePrice > 1000)
            discountFactor -= 0.03;
        return basePrice * discountFactor;
    }

    // 重构后
    public double getBasePrice(){
        return this.quantity * this.price;
    }
    public double discountFactor(){
        double discountFactor = 0.98;
        if (getBasePrice() > 1000)
            discountFactor -= 0.03;
        return discountFactor;
    }
    public double modificationGetPrice(){
        return getBasePrice() * discountFactor();
    }
}
