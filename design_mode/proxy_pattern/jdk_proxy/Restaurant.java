package design_mode.proxy_pattern.jdk_proxy;

public class Restaurant implements SellFood {
    @Override
    public void sell() {
        System.out.println("饭店卖食物");
    }
}
