package reconstruction.chapter11.remove_setting_method;

public class Test {
    public static void main(String[] args) {
        PersonBefore personBefore=new PersonBefore();
        personBefore.setId(1);
        personBefore.setName("A");

        // 只允许在创建对象时设置id
        PersonAfter personAfter=new PersonAfter(2);
        personBefore.setName("B");
    }
}
