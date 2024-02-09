package functional_programming.Stream.intermediate_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;

public class Filter {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 打印所有姓名长度大于1的作家的姓名
        authors.stream()
                .distinct()
                .filter(author -> author.getName().length()>1)
                .forEach(author -> System.out.println(author.getName()));
    }
}
