package reconstruction.chapter6.combine_function_into_transform;

public class CFIT {
    public  double baseRate(int month, int year) {
        return year*0.01+month;
    }
    public void client1(){
        Read data=new Read(4,2017,10,"ChenHao");
        double baseCharge=baseRate(data.getMonth(),data.getYear())*data.getQuantity();
        System.out.println(baseCharge);
    }
    public void client2(){
        Read data=new Read(4,2017,10,"ChenHao");
        double base=baseRate(data.getMonth(),data.getYear())*data.getQuantity();
        double taxableCharge=Math.max(0,base-100);
        System.out.println(taxableCharge);
    }
    public void client3(){
        Read data=new Read(4,2017,10,"ChenHao");
        double basicChargeAmount=calculateBaseCharge(data);
        System.out.println(basicChargeAmount);
    }

    public double calculateBaseCharge(Read data) {
        return baseRate(data.getMonth(),data.getYear())*data.getQuantity();
    }
}
