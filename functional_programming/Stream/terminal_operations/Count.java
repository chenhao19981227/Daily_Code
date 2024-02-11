package functional_programming.Stream.terminal_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;

public class Count {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 打印这些作家的所出书籍的数目，注意删除重复元素
        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);
    }
}
