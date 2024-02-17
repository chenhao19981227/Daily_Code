package functional_programming.method_reference;

import functional_programming.Stream.Author;

import java.util.List;

import static functional_programming.Stream.StreamDemo.getAuthors;

public class Example2 {
    public static void main(String[] args) {
        List<Author> authors = getAuthors();
        StringBuilder sb = new StringBuilder();
        authors.stream()
                .map(Author::getName)
                .forEach(sb::append);
        System.out.println(sb.toString());
    }
}
