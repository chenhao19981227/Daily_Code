package effective_java.chapter3_methods_common_to_all_objects.item11;

public class Test {
    public static void main(String[] args) {
        Person person1=new Person("a","1");
        Person person2=new Person("a","1");
        System.out.println(person1.equals(person2));


        PhoneNumber phoneNumber=new PhoneNumber(86,0663,1234);
        System.out.println(phoneNumber.hashCode());
        System.out.println(phoneNumber.hashCode2());
        System.out.println(phoneNumber.hashCode3());
    }
}
