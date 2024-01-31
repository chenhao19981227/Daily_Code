package reconstruction.chapter7.remove_middle_man;

public class Department {
    String chargeCode;
    String manger;

    public Department(String chargeCode, String manger) {
        this.chargeCode = chargeCode;
        this.manger = manger;
    }

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public String getManger() {
        return manger;
    }

    public void setManger(String manger) {
        this.manger = manger;
    }
}
