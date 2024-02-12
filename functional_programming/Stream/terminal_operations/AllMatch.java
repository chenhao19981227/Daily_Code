package functional_programming.Stream.terminal_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;

public class AllMatch {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 判断是否所有的作家都是成年人
        boolean flag = authors.stream()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println(flag);
    }
}
