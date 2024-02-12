package functional_programming.Stream.terminal_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;

public class NoneMatch {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 判断作家是否都没有超过100岁的
        boolean flag = authors.stream()
                .noneMatch(author -> author.getAge() > 100);
        System.out.println(flag);
    }
}
