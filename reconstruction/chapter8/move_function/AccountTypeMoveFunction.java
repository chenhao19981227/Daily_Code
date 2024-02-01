package reconstruction.chapter8.move_function;

public class AccountTypeMoveFunction {
    boolean isPremium;

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }
    public double overdraftCharge(double daysOverdrawn) {
        if (this.isPremium) {
            int baseCharge = 10;
            if (daysOverdrawn <= 7) {
                return baseCharge;
            } else {
                return baseCharge + (daysOverdrawn - 7) * 0.85;
            }
        } else {
            return daysOverdrawn * 1.75;
        }
    }
}
