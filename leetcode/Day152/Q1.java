package leetcode.Day152;

public class Q1 {
    public static int accountBalanceAfterPurchase(int purchaseAmount) {
        int temp=purchaseAmount;
        while (temp>10){
            temp%=10;
        }
        if(temp<5){
            return 100-purchaseAmount+temp;
        }
        return 100-purchaseAmount-temp+10;
    }

    public static void main(String[] args) {
        accountBalanceAfterPurchase(15);
    }
}
