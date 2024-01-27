package reconstruction.chapter6.combine_function_into_transform;

public class AfterCIFT {
    public void client1(){
        Read2 data=new Read2(4,2017,10,"ChenHao");
        double baseCharge=data.getBase();
        System.out.println(baseCharge);
    }
    public void client2(){
        Read2 data=new Read2(4,2017,10,"ChenHao");
        double taxableCharge=data.getTaxableCharge();
        System.out.println(taxableCharge);
    }
    public void client3(){
        Read2 data=new Read2(4,2017,10,"ChenHao");
        double basicChargeAmount=data.getBase();
        System.out.println(basicChargeAmount);
    }
}
