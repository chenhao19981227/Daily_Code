package reconstruction.chapter6.combine_function_into_transform;

public class Test {
    public static void main(String[] args) {
        CFIT test=new CFIT();
        test.client1();
        test.client2();
        test.client3();
        System.out.println("---------------------------");
        AfterCIFT test2=new AfterCIFT();
        test2.client1();
        test2.client2();;
        test2.client3();
    }
}
