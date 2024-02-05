package functional_programming.Lambda;

import java.util.function.IntConsumer;

public class Practice4 {
    public static void foreachArr(IntConsumer consumer){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            consumer.accept(i);
        }
    }

    public static void main(String[] args) {
        // 匿名内部类实现
        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });
        System.out.println("----------");
        // lambda表达式实现
        foreachArr(i-> System.out.println(i));
    }
}
