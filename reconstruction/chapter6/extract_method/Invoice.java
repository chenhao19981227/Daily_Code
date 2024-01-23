package reconstruction.chapter6.extract_method;

import java.time.LocalDate;

public class Invoice {
    Order[] orders;
    LocalDate dueDate;
    String customer;
    public Invoice(Order[] orders,LocalDate dueDate,String customer){
        this.orders=orders;
        this.dueDate=dueDate;
        this.customer=customer;
    }

    public Order[] getOrders() {
        return orders;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
