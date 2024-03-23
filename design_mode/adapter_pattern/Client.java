package design_mode.adapter_pattern;

public class Client {
    public static void main(String[] args) {
        Computer computer=new Computer();
        System.out.println(computer.readSD(new SDAdapterTF()));

        System.out.println(computer.readSD(new SDAdapterTF2(new TFCardImpl())));
    }
}
