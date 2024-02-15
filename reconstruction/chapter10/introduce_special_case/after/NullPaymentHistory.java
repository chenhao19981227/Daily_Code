package reconstruction.chapter10.introduce_special_case.after;

public class NullPaymentHistory extends PaymentHistory {
    public double getWeeksDelinquentInLastYears(){
        return 0;
    }
}
