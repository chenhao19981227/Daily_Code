package design_mode.flyweight_pattern;

import java.util.HashMap;
import java.util.Map;

public class BoxFactory {
    private static Map<String,Box> map;
    private BoxFactory(){
        map=new HashMap<>();
        Box iBox=new IBox();
        Box lBox=new LBox();
        map.put("I",iBox);
        map.put("L",lBox);
    }
    private static class SingletonHolder {
        private static final BoxFactory INSTANCE = new BoxFactory();
    }
    public static final BoxFactory getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public Box getBox(String shape){
        return map.get(shape);
    }
}
