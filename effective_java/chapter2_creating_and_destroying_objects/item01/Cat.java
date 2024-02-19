package effective_java.chapter2_creating_and_destroying_objects.item01;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    private static class SpyCat extends Cat{

    }
    int id;
    boolean isMale;
    String name;
    public static Cat getInstance(){
        return new Cat();
    };
    public static Cat getTomcat(int id,String name){
        return new Cat(id,true,name);
    }
    private static Map<String,Cat> catMap=new HashMap<>();
    public static Cat getNameNoRepeat(String name,Boolean isMale) {
        return catMap.computeIfAbsent(name,key->new Cat(1,isMale,key));
    }
    public static Cat getSpyCat(){
        return new SpyCat();
    }
    public static Cat getCatByNameLength(String name){
        return name.length()>5?new LongNameCat():new ShortNameCat();
    }
}
