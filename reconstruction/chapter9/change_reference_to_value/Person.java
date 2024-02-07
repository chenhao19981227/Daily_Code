package reconstruction.chapter9.change_reference_to_value;


public class Person {
    TelephoneNumber telephoneNumber;
    public String getAreaCode(){
        return this.telephoneNumber.getAreaCode();
    }
    public void setAreaCode(String areaCode){
        this.telephoneNumber.setAreaCode(areaCode);
    }
    public String getNumber(){
        return this.telephoneNumber.getNumber();
    }
    public void setNumber(String number){
        this.telephoneNumber.setNumber(number);
    }
}
