package effective_java.chapter3_methods_common_to_all_objects.item10;

public class Test {
    public static void main(String[] args) {
        PhoneNumber phoneNumber1=new PhoneNumber(86,0663,1234);
        PhoneNumber phoneNumber2=new PhoneNumber(86,0663,1234);
        System.out.println(phoneNumber1.equals(phoneNumber2));
    }
}
