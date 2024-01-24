package reconstruction.chapter6.extract_variable;

import java.util.Base64;

public class Order2 {
    // 可以由Order类来提供计算功能
    int quantity;
    double itemPrice;
    public Order2(int quantity, double itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getItemPrice() {
        return itemPrice;
    }
    public double getBasePrice(){
        return this.quantity*this.itemPrice;
    }
    public double getQuantityDiscount(){
        return Math.max(0,this.quantity-500)*this.itemPrice*0.05;
    }
    public double getShipping(){
        return Math.min(getBasePrice()*0.1,100);
    }
    public double price(){
        return getBasePrice() - getQuantityDiscount() + getShipping();
    }
}
