package functional_programming.method_reference;

import functional_programming.Stream.Author;

import java.util.List;

import static functional_programming.Stream.StreamDemo.getAuthors;

public class Example1 {
    public static void main(String[] args) {
        List<Author> authors = getAuthors();
        // 不适用方法引用
        authors.stream()
                .map(author -> author.getAge())
                .map(age->String.valueOf(age))
                .forEach(age-> System.out.println(age));
        // 使用方法引用
        authors.stream()
                .map(Author::getAge)
                .map(String::valueOf)
                .forEach(System.out::println);
    }
}
