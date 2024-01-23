package reconstruction.chapter6.extract_method;

import java.time.LocalDate;

public class ExtractMethod {
    public void printOwing(Invoice invoice){
        int outstanding = 0;
        System.out.println("***********************");
        System.out.println("**** Customer Owes ****");
        System.out.println("***********************");
        // calculate outstanding
        for (Order order : invoice.getOrders()) {
            outstanding += order.getAmount();
        }
        // record due date
        LocalDate localDate = LocalDate.now();
        invoice.setDueDate(LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth()));
        //print details
        System.out.println("name:" + invoice.getCustomer());
        System.out.println("amount:"+outstanding);
        System.out.println("due:"+invoice.getDueDate().toString());
    }
}
