package reconstruction.chapter11.replace_parameter_with_query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderBefore {
    int quantity;
    double itemPrice;
    public double finalPrice(){
        double basePrice=this.quantity*this.itemPrice;
        int discountLevel=0;
        if(this.quantity>100)discountLevel=2;
        else discountLevel=1;
        return this.discountedPrice(basePrice,discountLevel);
    }

    private double discountedPrice(double basePrice, int discountLevel) {
        switch (discountLevel){
            case 1:return basePrice*0.95;
            case 2:return basePrice*0.9;
            default:return basePrice;
        }
    }
}
