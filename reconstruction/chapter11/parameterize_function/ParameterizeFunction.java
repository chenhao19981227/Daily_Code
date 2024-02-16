package reconstruction.chapter11.parameterize_function;

public class ParameterizeFunction {
    public static void tenPercentRaise(int num){
        System.out.println(num*1.1);
    }
    public static void fivePercentRaise(int num){
        System.out.println(num*1.05);
    }

    public static void raise(int num,double factor){
        System.out.println(num*factor);
    }

    public static void main(String[] args) {
        tenPercentRaise(10);
        fivePercentRaise(10);
        System.out.println("-------------------");
        raise(10,1.1);
        raise(10,1.05);
    }
}
