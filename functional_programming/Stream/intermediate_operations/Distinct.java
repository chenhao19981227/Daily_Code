package functional_programming.Stream.intermediate_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;

public class Distinct {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 打印所有作家的姓名，并且要求其中不能有重复元素
        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
    }
}
