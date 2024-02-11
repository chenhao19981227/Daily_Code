package functional_programming.Stream.terminal_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;

public class AnyMatch {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 判断是否有年龄在29以上的作家
        boolean flag = authors.stream()
                .allMatch(author -> author.getAge() > 29);
        System.out.println(flag);
    }
}
