package functional_programming.Stream.intermediate_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;

public class Limit {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素,然后打印其中年龄最大的两个作家的姓名
        authors.stream()
                .distinct()
                .sorted(((o1, o2) -> o2.getAge()-o1.getAge()))
                .limit(2)
                .forEach(author -> System.out.println(author.getName()));
    }
}
