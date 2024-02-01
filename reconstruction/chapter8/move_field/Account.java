package reconstruction.chapter8.move_field;

public class Account {
    int number;
    AccountType type;
    double interestRate;

    public Account(int number, AccountType type, double interestRate) {
        this.number = number;
        this.type = type;
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return this.interestRate;
    }
}
