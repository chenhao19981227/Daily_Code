package reconstruction.chapter10.introduce_special_case.before;

import lombok.Data;

@Data
public class Customer {
    String name;
    String plan;
    PaymentHistory paymentHistory;
}
