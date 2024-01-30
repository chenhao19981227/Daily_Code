package reconstruction.chapter7.replace_primitive_with_object;
//现在我们把address这个数值搬出来作为一个类
//修改后代码如下
public class UserEPWO {
    private Address address;
    public UserEPWO(String address) {
        this.address = new Address(address);
    }
    public String getAddress() {
        return address.getDetail();
    }
    public void setAddress(String address) {
        this.address = new Address(address);//这里每次set都会new一个Address，会产生大量实例，可以优化！
    }
}
