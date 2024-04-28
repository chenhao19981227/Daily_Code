package effective_java.chapter5_generic.item26;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add("str");
        for (int i = 0; i < list.size(); i++) {
            Integer num=(Integer) list.get(i);
        }
    }
}
