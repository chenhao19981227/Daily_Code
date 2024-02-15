package reconstruction.chapter10.introduce_special_case.after;

public class UnknownCustomer extends Customer{
    @Override
    public boolean isUnknown() {
        return true;
    }
    @Override
    public String getName() {
        return "occupant";
    }

    @Override
    public PaymentHistory getPaymentHistory() {
        return new NullPaymentHistory();
    }
}
