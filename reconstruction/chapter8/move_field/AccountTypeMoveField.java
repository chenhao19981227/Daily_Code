package reconstruction.chapter8.move_field;

public class AccountTypeMoveField {
    String name;
    double interestRate;
    public AccountTypeMoveField(String name,double interestRate) {
        this.name = name;
        this.interestRate=interestRate;
    }

    public double getInterestRate() {
        return this.interestRate;
    }
}
