package effective_java.chapter4_classes_and_interfaces.item18;

import java.util.HashSet;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        MyHashSet<String> myHashSet=new MyHashSet();
        myHashSet.addAll(List.of("a","b","c"));
        System.out.println(myHashSet.getAddCount());

        MyHashSet2<String> myHashSet2=new MyHashSet2<>(new HashSet<>());
        myHashSet2.addAll(List.of("a","b","c"));
        System.out.println(myHashSet2.getAddCount());
    }
}
