package functional_programming.Stream.terminal_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.Book;
import functional_programming.Stream.StreamDemo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Collect {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 获取一个存放所有作者名字的List集合
        List<String> nameList = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(nameList);

        // 获取一个所有书名的Set集合
        Set<String> bookNameSet = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getName())
                .collect(Collectors.toSet());
        System.out.println(bookNameSet);

        // 获取一个Map集合，map的key为作者名，value为book的List
        Map<String, List<Book>> map = authors.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
        for (Map.Entry<String, List<Book>> stringListEntry : map.entrySet()) {
            System.out.println(stringListEntry);
        }
    }
}
