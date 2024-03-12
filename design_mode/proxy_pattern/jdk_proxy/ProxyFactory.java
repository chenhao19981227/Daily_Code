package design_mode.proxy_pattern.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    private Restaurant restaurant=new Restaurant();
    public SellFood getProxyObject(){
        SellFood sellFood=(SellFood)Proxy.newProxyInstance(restaurant.getClass().getClassLoader(), restaurant.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("美团外卖，收取配送费");
                        return method.invoke(restaurant, args);
                    }
                });
        return sellFood;
    }
}
