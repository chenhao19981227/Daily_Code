package functional_programming.Stream.intermediate_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.Arrays;
import java.util.List;

public class FlatMap {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 打印所有书籍的名字。要求对重复的元素进行去重
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
        // 打印现有数据的所有分类。要求对分类进行去重。不能出现这种格式：哲学,爱情
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(category-> System.out.println(category));
    }
}
