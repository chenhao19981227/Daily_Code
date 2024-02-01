package reconstruction.chapter8.move_function;

public class Account {
    double daysOverdrawn;
    AccountType type;
    public double bankCharge(){
        double result = 4.5;
        if (this.daysOverdrawn > 0)
            result += this.overdraftCharge();
        return result;
    }
    public double overdraftCharge() {
        if (this.type.isPremium) {
            int baseCharge = 10;
            if (this.daysOverdrawn <= 7) {
                return baseCharge;
            } else {
                return baseCharge + (this.daysOverdrawn - 7) * 0.85;
            }
        } else {
            return this.daysOverdrawn * 1.75;
        }
    }
}
