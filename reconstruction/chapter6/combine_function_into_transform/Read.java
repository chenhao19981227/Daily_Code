package reconstruction.chapter6.combine_function_into_transform;

public class Read {
    int month;
    int year;
    int quantity;
    String customer;

    public Read(int month, int year, int quantity, String customer) {
        this.month = month;
        this.year = year;
        this.quantity = quantity;
        this.customer = customer;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCustomer() {
        return customer;
    }
}
