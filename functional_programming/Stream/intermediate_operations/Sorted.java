package functional_programming.Stream.intermediate_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;

public class Sorted {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素
        // 方法1：使用无参的sorted方法，这种方法需要Author实现Comparable,并重写其中的方法
        authors.stream()
                .distinct()
                .sorted()
                .forEach(author -> System.out.println(author.getAge()));
        // 方法2：使用带参数sorted方法，在sorted方法中重写排序逻辑
        authors.stream()
                .distinct() .sorted((o1, o2) -> o2.getAge()-o1.getAge())
                .forEach(author -> System.out.println(author.getAge()));
    }
}
