package functional_programming.Stream.terminal_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;
import java.util.Optional;

public class FindFirst {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 获取一个年龄最小的作家，并输出他的姓名
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
        first.ifPresent(author -> System.out.println(author.getName()));
    }
}
