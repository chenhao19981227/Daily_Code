package reconstruction.chapter9.change_reference_to_value;

public class PersonAfterChange {
    TelephoneAfterChange telephoneNumber;
    public String getAreaCode(){
        return this.telephoneNumber.getAreaCode();
    }
    public void setAreaCode(String areaCode){
        this.telephoneNumber=new TelephoneAfterChange(areaCode,telephoneNumber.getNumber());
    }
    public String getNumber(){
        return this.telephoneNumber.getNumber();
    }
    public void setNumber(String number){
        this.telephoneNumber=new TelephoneAfterChange(telephoneNumber.getAreaCode(),number);
    }
}
