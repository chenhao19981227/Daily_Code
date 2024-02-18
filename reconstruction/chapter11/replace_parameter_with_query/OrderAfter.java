package reconstruction.chapter11.replace_parameter_with_query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderAfter {
    int quantity;
    double itemPrice;
    public double finalPrice(){
        double basePrice=this.quantity*this.itemPrice;
        return this.discountedPrice(basePrice);
    }

    private int getDiscountLevel() {
        return this.quantity > 100 ? 2 : 1;
    }

    private double discountedPrice(double basePrice) {
        switch (this.getDiscountLevel()){
            case 1:return basePrice*0.95;
            case 2:return basePrice*0.9;
            default:return basePrice;
        }
    }
}
