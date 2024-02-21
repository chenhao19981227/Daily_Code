package effective_java.chapter2_creating_and_destroying_objects.item02;

import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class Cat1 {
    private Integer id;// id，唯一，不可变，不可为null
    private String name;// 名字，不唯一，可变，可为null
    private String color;// 颜色，不唯一，不可变，不可为null
    private LocalDateTime birthday;// 生日，不唯一，不可变，不可为null
    private Integer masterId;// 主人，不唯一，可变，可为null
    public Cat1(){}
    public Cat1(Integer id, String name, String color, LocalDateTime birthday, Integer masterId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.birthday = birthday;
        this.masterId = masterId;
    }
    public Cat1(Integer id, String color, LocalDateTime birthday, Integer masterId) {
        this.id = id;
        this.color = color;
        this.birthday = birthday;
        this.masterId = masterId;
    }
    public Cat1(Integer id, String name, String color, LocalDateTime birthday) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.birthday = birthday;
    }
    public Cat1(Integer id, String color, LocalDateTime birthday) {
        this.id = id;
        this.color = color;
        this.birthday = birthday;
    }
}
