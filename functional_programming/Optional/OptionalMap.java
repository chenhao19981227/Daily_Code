package functional_programming.Optional;

import functional_programming.Stream.Author;
import functional_programming.Stream.Book;

import java.util.List;
import java.util.Optional;

public class OptionalMap {
    public static void main(String[] args) {
        Author author = GetAuthor.getAuthor();
        Optional<Author> authorOptional = Optional.ofNullable(author);
        Optional<List<Book>> books = authorOptional.map(author1 -> author1.getBooks());
        books.ifPresent(books1 -> System.out.println(books1));
    }
}
