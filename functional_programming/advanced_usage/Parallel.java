package functional_programming.advanced_usage;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;

public class Parallel {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();

        authors.stream()
                .map(Author::getAge)
                .map(age -> age + 10)
                .filter(age->age>18)
                .map(age->age+2)
                .forEach(integer -> System.out.println(integer+":"+Thread.currentThread().getName()));
        System.out.println("-----------------------");
        // 使用parallel
        authors.stream()
                .parallel()
                .map(Author::getAge)
                .map(age -> age + 10)
                .filter(age->age>18)
                .map(age->age+2)
                .forEach(integer -> System.out.println(integer+":"+Thread.currentThread().getName()));
        System.out.println("----------------------");
        // 使用parallelStream
        authors.parallelStream()
                .map(Author::getAge)
                .map(age -> age + 10)
                .filter(age->age>18)
                .map(age->age+2)
                .forEach(integer -> System.out.println(integer+":"+Thread.currentThread().getName()));
    }
}
