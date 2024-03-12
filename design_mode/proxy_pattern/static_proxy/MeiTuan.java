package design_mode.proxy_pattern.static_proxy;

public class MeiTuan implements SellFood{
    private final Restaurant restaurant=new Restaurant();
    @Override
    public void sell() {
        System.out.println("美团外卖，收取配送费");
        restaurant.sell();
    }
}
