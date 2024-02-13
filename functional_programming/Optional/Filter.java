package functional_programming.Optional;

import functional_programming.Stream.Author;

import java.util.Optional;

public class Filter {
    public static void main(String[] args) {
        Author author = GetAuthor.getAuthor();
        Optional<Author> authorOptional = Optional.ofNullable(author);
        authorOptional.ifPresent(author1 -> System.out.println(author1.getAge()));
        authorOptional.filter(author12 -> author12.getAge()>50).ifPresent(author1 -> System.out.println(author1.getAge()));
    }
}
