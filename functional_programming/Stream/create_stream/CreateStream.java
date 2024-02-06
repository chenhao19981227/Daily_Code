package functional_programming.Stream.create_stream;

import java.util.*;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) {
        // 单列集合
        List<Integer> list=new ArrayList<>();
        Stream<Integer> stream = list.stream();
        // 数组
        Integer[] arr = {1,2,3,4,5};
        Stream<Integer> stream1 = Arrays.stream(arr);
        Stream<Integer> stream2 = Stream.of(arr);
        // 双列集合
        Map<String,Integer> map = new HashMap<>();
        map.put("蜡笔小新",19);
        map.put("黑子",17);
        map.put("日向翔阳",16);
        Stream<Map.Entry<String, Integer>> stream3 = map.entrySet().stream();
    }
}
