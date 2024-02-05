package functional_programming.Lambda;

import java.util.function.IntBinaryOperator;

public class Practice1 {
    public static int calculateNum(IntBinaryOperator operator){
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }
    public static void main(String[] args) {
        // 匿名内部类写法
        System.out.println(calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        }));
        // Lambda写法
        System.out.println(calculateNum(((left, right) -> left+right)));
    }
}
