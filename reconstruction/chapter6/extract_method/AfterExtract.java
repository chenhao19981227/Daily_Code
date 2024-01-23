package reconstruction.chapter6.extract_method;

import java.time.LocalDate;

public class AfterExtract {
    public void printOwing(Invoice invoice){
        printBanner();
        recordDueDate(invoice);
        printDetail(invoice);
    }
    public void printBanner(){
        System.out.println("***********************");
        System.out.println("**** Customer Owes ****");
        System.out.println("***********************");
    }
    public int calOutstanding(Invoice invoice){
        int result=0;
        for (Order order : invoice.getOrders()) {
            result += order.getAmount();
        }
        return result;
    }
    public void recordDueDate(Invoice invoice){
        // record due date
        LocalDate localDate = LocalDate.now();
        invoice.setDueDate(LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth()));
    }
    public void printDetail(Invoice invoice){
        //print details
        System.out.println("name:" + invoice.getCustomer());
        System.out.println("amount:"+calOutstanding(invoice));
        System.out.println("due:"+invoice.getDueDate().toString());
    }
}
