package functional_programming.method_reference;

import functional_programming.Stream.Author;

import java.util.List;

import static functional_programming.Stream.StreamDemo.getAuthors;

public class Example4 {
    public static void main(String[] args) {
        List<Author> authors = getAuthors();
        authors.stream()
                .map(author -> author.getName())
                .map(name->new StringBuilder(name))
                .map(sb->sb.append("-haha").toString())
                .forEach(str-> System.out.println(str));
        System.out.println("-------------------------");
        authors.stream()
                .map(Author::getName)
                .map(StringBuilder::new)
                .map(sb->sb.append("-haha").toString())
                .forEach(System.out::println);
    }
}
