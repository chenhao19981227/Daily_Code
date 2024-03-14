package effective_java.chapter4_classes_and_interfaces.item18;

import java.util.Collection;
import java.util.HashSet;

public class MyHashSet<E> extends HashSet<E> {
    private int addCount=0;
    public MyHashSet(){};
    public MyHashSet(int initCap,float loadFactor){
        super(initCap,loadFactor);
    }
    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount+=c.size();
        return super.addAll(c);
    }
    public int getAddCount(){
        return addCount;
    }
}
