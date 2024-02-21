package effective_java.chapter2_creating_and_destroying_objects.item02;

import lombok.ToString;

import java.time.LocalDateTime;
@ToString
public class Cat2 {
    private final Integer id;// id，唯一，不可变，不可为null
    private String name;// 名字，不唯一，可变，可为null
    private String color;// 颜色，不唯一，不可变，可为null
    private final LocalDateTime birthday;// 生日，不唯一，不可变，不可为null
    private Integer masterId;// 主人，不唯一，可变，可为null

    public Cat2(Integer id, LocalDateTime birthday) {
        this.id = id;
        this.birthday = birthday;
    }
    public static class CatBuilder{
        private final Integer id;// id，唯一，不可变，不可为null
        private String name;// 名字，不唯一，可变，可为null
        private String color;// 颜色，不唯一，不可变，可为null
        private final LocalDateTime birthday;// 生日，不唯一，不可变，不可为null
        private Integer masterId;// 主人，不唯一，可变，可为null
        public CatBuilder(Integer id,LocalDateTime birthday){
            this.id=id;
            this.birthday=birthday;
        }
        public CatBuilder name(String name){
            this.name=name;
            return this;
        }
        public CatBuilder color(String color){
            this.color=color;
            return this;
        }
        public CatBuilder masterId(Integer masterId){
            this.masterId=masterId;
            return this;
        }
        public Cat2 build(){
            return new Cat2(this);
        }
    }
    private Cat2(CatBuilder builder){
        id=builder.id;
        name=builder.name;
        color=builder.color;
        birthday=builder.birthday;
        masterId=builder.masterId;
    }
}
