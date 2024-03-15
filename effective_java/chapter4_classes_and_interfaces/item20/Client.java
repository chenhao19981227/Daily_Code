package effective_java.chapter4_classes_and_interfaces.item20;

public class Client {
    public static void main(String[] args) {
        MySummation mySummation=new MySummation();
        Object[] nums= new Object[]{1,2,3};
        System.out.println(mySummation.arrayEleSum(nums));

        MySummation2 mySummation2=new MySummation2();
        System.out.println(mySummation2.arrayEleSum(nums));
    }
}
