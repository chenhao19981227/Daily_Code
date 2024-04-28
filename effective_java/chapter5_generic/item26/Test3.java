package effective_java.chapter5_generic.item26;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        add(list,1);
        add(list,"str");
        for (Integer i : list) {
            System.out.println(i);
        }
    }
    private static void add(List list,Object o){
        list.add(o);
    }
}
