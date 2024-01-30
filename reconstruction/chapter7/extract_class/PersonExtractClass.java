package reconstruction.chapter7.extract_class;

public class PersonExtractClass {
    private String name;
    private TelephoneNumber telephoneNumber;

    public PersonExtractClass(String name, TelephoneNumber telephoneNumber) {
        this.name = name;
        this.telephoneNumber=telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber.toString();
    }
    public String getOfficeAreaCode() {
        return this.telephoneNumber.getAreaCode();
    }

    public void setOfficeAreaCode(String officeAreaCode) {
        this.telephoneNumber.setAreaCode(officeAreaCode);
    }

    public String getOfficeNumber() {
        return this.telephoneNumber.getNumber();
    }

    public void setOfficeNumber(String officeNumber) {
        this.telephoneNumber.setNumber(officeNumber);
    }
    public void setTelephoneNumber(TelephoneNumber telephoneNumber){
        this.telephoneNumber=telephoneNumber;
    }
}
