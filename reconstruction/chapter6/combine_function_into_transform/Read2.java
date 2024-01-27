package reconstruction.chapter6.combine_function_into_transform;

public class Read2 {
    int month;
    int year;
    int quantity;
    String customer;

    public Read2(int month, int year, int quantity, String customer) {
        this.month = month;
        this.year = year;
        this.quantity = quantity;
        this.customer = customer;
    }
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    private double baseRate(int month,int year){
        return year*0.01+month;
    }


    public double getBase(){
        return baseRate(month,year)*quantity;
    }
    public double getTaxableCharge(){
        return Math.max(0,getBase()-100);
    }
}
