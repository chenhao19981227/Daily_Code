package reconstruction.chapter11.replace_parameter_with_query;

public class Test {
    public static void main(String[] args) {
        OrderBefore orderBefore=new OrderBefore(105,2);
        System.out.println(orderBefore.finalPrice());
        System.out.println("-----------------------");
        OrderAfter orderAfter=new OrderAfter(105,2);
        System.out.println(orderAfter.finalPrice());
    }
}
