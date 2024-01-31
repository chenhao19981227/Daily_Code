package reconstruction.chapter7.inline_class;



public class Person {

    private TelephoneNumber officeTelephone = new TelephoneNumber();
    private String name;

    public String getName() {
        return name;
    }
    public String getTelephoneNumber() {
        return officeTelephone.getTelephoneNumber();
    }
    public String getAreaCode(){
        return this.officeTelephone.getAreaCode();
    }
    public String getNumber(){
        return this.officeTelephone.getNumber();
    }
}
