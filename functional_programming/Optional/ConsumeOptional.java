package functional_programming.Optional;

import functional_programming.Stream.Author;

import java.util.Optional;
import java.util.function.Supplier;

public class ConsumeOptional {
    public static void main(String[] args) {
        Author author = GetAuthor.getAuthor();
        // 使用ifPresent安全消费值
        Optional<Author> authorOptional = Optional.ofNullable(author);
        authorOptional.ifPresent(author1 -> System.out.println(author1.getName()));
        // 使用orElseGet安全获取值，在为空时返回传入的默认值
        authorOptional.orElseGet(() -> new Author());
        // 使用orElseThrow，在为空时抛出指定异常
        try {
            authorOptional.orElseThrow((Supplier<Throwable>) () -> new RuntimeException("author为空"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
