package reconstruction.chapter6.extract_method;

import java.time.LocalDate;

public class test {
    public static void main(String[] args) {
        ExtractMethod extractMethod=new ExtractMethod();
        Order[] orders=new Order[]{new Order(1),new Order(2)};
        LocalDate localDate=LocalDate.now();
        String customer="ChenHao";
        Invoice invoice=new Invoice(orders,localDate,customer);
        extractMethod.printOwing(invoice);
    }
}
