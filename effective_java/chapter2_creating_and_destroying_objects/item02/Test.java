package effective_java.chapter2_creating_and_destroying_objects.item02;

import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        Cat1 cat1=new Cat1();
        cat1.setId(1);
        cat1.setName("mao");

        Cat2 cat2 = new Cat2.CatBuilder(2, LocalDateTime.now())
                .name("maomao")
                .color("white")
                .masterId(10)
                .build();
        System.out.println(cat2);
        Cat3 cat3=new Cat3.Cat3Builder()
                .id(3)
                .birthday(LocalDateTime.now())
                .name("maomao")
                .color("white")
                .masterId(10)
                .build();
        System.out.println(cat3);
    }
}
