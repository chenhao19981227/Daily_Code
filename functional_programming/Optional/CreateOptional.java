package functional_programming.Optional;

import functional_programming.Stream.Author;
import java.util.Optional;

public class CreateOptional {
    public static void main(String[] args) {
        Author author = GetAuthor.getAuthor();
        // 使用ofNullable创建Optional对象
        Optional<Author> authorOptional = Optional.ofNullable(author);
        // 使用of创建Optional对象
        Optional<Author> authorOptional1 = Optional.of(author);
        // of搭配empty防止Optional为空
        Optional<Author> authorOptional2=author==null?Optional.empty():Optional.of(author);
    }
}
