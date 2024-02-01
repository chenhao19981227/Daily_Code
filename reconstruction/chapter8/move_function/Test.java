package reconstruction.chapter8.move_function;

public class Test {
    public static void main(String[] args) {
        Account account=new Account();
        AccountType accountType=new AccountType();
        accountType.setPremium(false);
        account.type=accountType;
        account.daysOverdrawn=10;
        System.out.println(account.bankCharge());

        System.out.println("---------------------------");
    }
}
