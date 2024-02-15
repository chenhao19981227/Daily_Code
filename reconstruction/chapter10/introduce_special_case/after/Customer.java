package reconstruction.chapter10.introduce_special_case.after;

import lombok.Data;

@Data
public class Customer {
    String name;
    String plan;
    PaymentHistory paymentHistory;

    public boolean isUnknown() {
        return false;
    }
}
