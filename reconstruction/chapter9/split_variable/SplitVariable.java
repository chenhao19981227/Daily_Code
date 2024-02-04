package reconstruction.chapter9.split_variable;

public class SplitVariable {
    public static int discount(int inputValue,int quantity){
        if(inputValue>50)inputValue=inputValue-2;
        if(quantity>100)inputValue=inputValue-1;
        return inputValue;
    }
    public static int discountSplitVariable(int inputValue,int quantity){
        int result=inputValue;
        if(inputValue>50)result-=2;
        if(quantity>100)result-=1;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(discount(80,120));
        System.out.println(discountSplitVariable(80,120));
    }
}
