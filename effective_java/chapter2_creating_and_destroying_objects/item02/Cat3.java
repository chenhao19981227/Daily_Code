package effective_java.chapter2_creating_and_destroying_objects.item02;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@ToString
public class Cat3 {
    private final Integer id;// id，唯一，不可变，不可为null
    private String name;// 名字，不唯一，可变，可为null
    private String color;// 颜色，不唯一，不可变，可为null
    private final LocalDateTime birthday;// 生日，不唯一，不可变，不可为null
    private Integer masterId;// 主人，不唯一，可变，可为null

    public Cat3(Integer id, LocalDateTime birthday) {
        this.id = id;
        this.birthday = birthday;
    }
}
