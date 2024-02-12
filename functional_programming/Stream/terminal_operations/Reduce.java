package functional_programming.Stream.terminal_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;
import java.util.Optional;

public class Reduce {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 使用reduce求所有作者年龄的和
        Integer sum = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, (result, element) -> result + element);
        System.out.println(sum);
         // 使用reduce求所有作者中年龄的最大值
        Integer max = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, ((integer, integer2) -> integer > integer2 ? integer : integer2));
        System.out.println(max);
        // 使用reduce求所有作者中年龄的最小值
        Integer min = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, ((integer, integer2) -> integer < integer2 ? integer : integer2));
        System.out.println(min);
        // 使用reduce一个参数的重载方法求所有作者中年龄的最小值
        Optional<Integer> min2 = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce((integer, integer2) -> integer < integer2 ? integer : integer2);
        System.out.println(min2.get());
    }
}
