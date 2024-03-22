package design_mode.facade_pattern;

public class Client {
    public static void main(String[] args) {
        SmartAppliancesFacade smartAppliancesFacade=new SmartAppliancesFacade();
        smartAppliancesFacade.say("打开家电");
        smartAppliancesFacade.say("关闭家里");
    }
}
