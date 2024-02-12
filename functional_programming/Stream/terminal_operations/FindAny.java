package functional_programming.Stream.terminal_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;
import java.util.Optional;

public class FindAny {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 获取任意一个年龄大于18的作家，如果存在就输出他的名字
        Optional<Author> writer = authors.stream()
                .filter(author -> author.getAge() > 18)
                .findAny();
        writer.ifPresent(author -> System.out.println(author.getName()));
    }
}
