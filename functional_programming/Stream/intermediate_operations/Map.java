package functional_programming.Stream.intermediate_operations;

import functional_programming.Stream.Author;
import functional_programming.Stream.StreamDemo;

import java.util.List;

public class Map {
    public static void main(String[] args) {
        List<Author> authors = StreamDemo.getAuthors();
        // 获取所有作家的姓名，并打印所有作家的姓名
        authors.stream()
                .map(author -> author.getName())
                .forEach(name-> System.out.println(name));

        // 获取所有作家的年龄，并增加10岁
        authors.stream()
                .map(author -> author.getAge())
                .map(age->age+10)
                .forEach(age-> System.out.println(age));
    }
}
