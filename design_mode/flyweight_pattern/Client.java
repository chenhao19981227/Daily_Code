package design_mode.flyweight_pattern;

public class Client {
    public static void main(String[] args) {
        BoxFactory boxFactory=BoxFactory.getInstance();
        Box ibox = boxFactory.getBox("I");
        System.out.println(ibox.getShape());
    }
}
