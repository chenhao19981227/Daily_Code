package effective_java.chapter4_classes_and_interfaces.item21;

public class Test {
    public static void main(String[] args) {
        User user=new User();
        user.setPasswordIf("b", s -> !s.isEmpty());
        System.out.println(user.getPassword());

        MyUser myUser=new MyUser();
        myUser.setPasswordIf("c",s -> !s.isEmpty());
        System.out.println(myUser.getPassword());
    }
}
