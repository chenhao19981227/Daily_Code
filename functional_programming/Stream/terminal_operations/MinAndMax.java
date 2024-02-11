package functional_programming.Stream.terminal_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;
import java.util.Optional;

public class MinAndMax {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 分别获取这些作家的所出书籍的最高分和最低分并打印
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((score1, score2) -> score1 - score2);
        System.out.println(max.get());
        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((score1, score2) -> score1 - score2);
        System.out.println(min.get());
    }
}
