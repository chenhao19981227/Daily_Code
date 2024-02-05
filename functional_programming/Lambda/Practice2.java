package functional_programming.Lambda;

import java.util.function.IntPredicate;

public class Practice2 {
    // 打印偶数
    public static void printEvenNum(IntPredicate predicate){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            if(predicate.test(i)){
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args) {
        // 匿名内部类实现
        printEvenNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value % 2 == 0;
            }
        });
        System.out.println("------------------");
        // 手动lambda实现
        printEvenNum(value -> value%2==0);
        System.out.println("------------------");
        // alt+enter自动实现
        printEvenNum(value -> value % 2 == 0);
    }
}
