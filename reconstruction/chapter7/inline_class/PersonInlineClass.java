package reconstruction.chapter7.inline_class;

public class PersonInlineClass {
    private String name;
    private String areaCode;
    private String number;

    public String getName() {
        return name;
    }
    public String getTelephoneNumber() {
        return this.areaCode+"-"+this.number;
    }
    public String getAreaCode(){
        return this.areaCode;
    }
    public String getNumber(){
        return this.number;
    }

}
