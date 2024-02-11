package functional_programming.Stream.terminal_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;

public class ForEach {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 输出所有作家的名字
        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
    }
}
