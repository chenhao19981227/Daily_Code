package functional_programming.advanced_usage;

import functional_programming.Stream.Author;

import java.util.List;

import static functional_programming.Stream.StreamDemo.getAuthors;

public class DataType {
    public static void main(String[] args) {
        List<Author> authors = getAuthors();
        authors.stream()
                .map(Author::getAge)
                .map(age -> age + 10)
                .filter(age->age>18)
                .map(age->age+2)
                .forEach(System.out::println);
        System.out.println("-------------------");
        authors.stream()
                // 直接写成int类型，防止后续重复自动拆箱和装箱
                .mapToInt(Author::getAge)
                .map(age -> age + 10)
                .filter(age->age>18)
                .map(age->age+2)
                .forEach(System.out::println);
    }
}
