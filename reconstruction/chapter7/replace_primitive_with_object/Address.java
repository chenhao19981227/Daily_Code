package reconstruction.chapter7.replace_primitive_with_object;
//假设有个代表用户的类User, 里面有个数值叫address,我希望改用一个对象来表示地址，以后就更好扩展
//原代码
public class Address {
    private String detail;
    public Address(String detail) {
        this.detail = detail;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
