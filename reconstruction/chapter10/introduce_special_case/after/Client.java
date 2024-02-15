package reconstruction.chapter10.introduce_special_case.after;

public class Client {
    public static void main(String[] args) {
        Site site=new Site();
        Customer customer = site.getCustomer();
        // 第一种情况
        String customerName=customer.getName();
        // 第二种情况
        String plan=customer.isUnknown()?"plan A":customer.getPlan();
        // 第三种情况
        String newPlan="plan B";
        if(!customer.isUnknown())customer.setPlan(newPlan);
        // 第四种情况
        double weeksDelinquent=customer.getPaymentHistory().getWeeksDelinquentInLastYears();
        System.out.println(customerName);
        System.out.println(plan);
        System.out.println(weeksDelinquent);
    }
}
