package design_mode.proxy_pattern.cglib_proxy;

public class Client {
    public static void main(String[] args) {
        MeiTuan meiTuan=new MeiTuan();
        Restaurant proxyObject = meiTuan.getProxyObject();
        proxyObject.sell();
    }
}
