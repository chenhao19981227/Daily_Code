package functional_programming.Optional;

import functional_programming.Stream.Author;
import functional_programming.Stream.Book;

import java.util.ArrayList;
import java.util.List;

public class GetAuthor {
    public static Author getAuthor(){
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        List<Book> book = new ArrayList<>();
        book.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        book.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));
        author.setBooks(book);
        return author;
    }
}
