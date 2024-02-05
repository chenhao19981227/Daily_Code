package functional_programming.Lambda;

import java.util.function.Function;

public class Practice3 {
    public static <R> R typeConver(Function<String,R> function){
        String str = "1235";
        R result = function.apply(str);
        return result;
    }

    public static void main(String[] args) {
        // 匿名内部类实现
        System.out.println(typeConver(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        }));
        // lambda实现
        System.out.println(typeConver(s -> Integer.valueOf(s)).intValue());
    }
}
