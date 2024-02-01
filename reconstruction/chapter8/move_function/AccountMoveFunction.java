package reconstruction.chapter8.move_function;

public class AccountMoveFunction {
    double daysOverdrawn;
    AccountTypeMoveFunction type;
    public double bankCharge(){
        double result = 4.5;
        if (this.daysOverdrawn > 0)
            result += this.type.overdraftCharge(this.daysOverdrawn);
        return result;
    }
}
