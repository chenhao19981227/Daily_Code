package design_mode.proxy_pattern.jdk_proxy;


public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory=new ProxyFactory();
        SellFood proxyObject = proxyFactory.getProxyObject();
        proxyObject.sell();
    }
}
