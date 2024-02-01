package reconstruction.chapter8.move_field;

public class AccountMoveField {
    int number;
    AccountTypeMoveField type;
    public AccountMoveField(int number, AccountTypeMoveField type) {
        this.number = number;
        this.type = type;
    }

    public double getInterestRate() {
        return this.type.getInterestRate();
    }
}
