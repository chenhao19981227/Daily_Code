package effective_java.chapter3_methods_common_to_all_objects.item13;

public class Test {
    public static void main(String[] args) {
        User u1=new User(1,"c");
        User u2 = u1.clone();
        System.out.println(u1);
        System.out.println(u2);

        System.out.println("------------------------");
        SubUser s1=new SubUser(2,"b",new String[]{"xx","cc"});
        SubUser s2 = s1.clone();
        s2.address[0]="dd";
        System.out.println(s1.address[0]);
    }
}
