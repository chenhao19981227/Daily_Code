package reconstruction.chapter10.decompose_conditional;

public class DecomposeConditional {
    public static void main(String[] args) {
        Date aDate=new Date(7);
        Plan plan=new Plan(6,8,0.8,0.9,10);
        double charge=0;
        int quantity=10;
        if (summer(aDate, plan))
            charge = summerCharge(quantity, plan);
        else
            charge = regularCharge(quantity, plan);
        System.out.println(charge);

        System.out.println("重构后");
        charge=summer(aDate,plan)?summerCharge(quantity,plan):regularCharge(quantity,plan);
        System.out.println(charge);
    }

    private static double regularCharge(int quantity, Plan plan) {
        return quantity * plan.getRegularRate() + plan.getRegularServiceCharge();
    }

    private static double summerCharge(int quantity, Plan plan) {
        return quantity * plan.getSummerRate();
    }

    private static boolean summer(Date aDate, Plan plan) {
        return aDate.getMonth() > plan.getSummerStart() && aDate.getMonth() < plan.getSummerEnd();
    }

}
