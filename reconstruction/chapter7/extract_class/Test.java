package reconstruction.chapter7.extract_class;

public class Test {
    public static void main(String[] args) {
        Person person1=new Person("ChenHao","0663","131221");
        System.out.println("name:"+person1.getName()+"\n"+"phoneNumber:"+person1.getTelephoneNumber());
        System.out.println("-----------------------");
        TelephoneNumber telephoneNumber=new TelephoneNumber("0663","131221");
        PersonExtractClass person2= new PersonExtractClass("ChenHao",telephoneNumber);
        System.out.println("name:"+person2.getName()+"\n"+"phoneNumber:"+person2.getTelephoneNumber());
    }
}
