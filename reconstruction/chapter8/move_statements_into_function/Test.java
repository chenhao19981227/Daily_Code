package reconstruction.chapter8.move_statements_into_function;

public class Test {
    public static void main(String[] args) {
        MSIF msif=new MSIF();
        Person person=new Person();
        Photo photo=new Photo();
        photo.setLocation("GD");
        photo.setTitle("Photo");
        person.setPhoto(photo);
        person.setName("ChenHao");
        System.out.println(msif.renderPerson(person).toString());
        System.out.println(msif.photoDiv(photo));
        System.out.println("----------------------------------");
        AfterMSIF afterMSIF=new AfterMSIF();
        System.out.println(afterMSIF.renderPerson(person).toString());
        System.out.println(afterMSIF.photoDiv(photo));
    }
}
