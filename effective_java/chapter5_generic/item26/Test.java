package effective_java.chapter5_generic.item26;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List list=new ArrayList<>();
        list.add(0);
        list.add(1L);
        list.add(2D);
        list.add("3str");
        System.out.println(list);
    }
}
