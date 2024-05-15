package leetcode.Day125;

import java.util.*;

public class Q1 {
    private LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
    private int capacity;
    public Q1(int capacity) {
        //时间排序
        this.capacity = capacity;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int cacheSize=sc.nextInt();
        int op=sc.nextInt();
        sc.nextLine();
        Q1 lru=new Q1(cacheSize);
        for (int i = 0; i < op; i++) {
            String str=sc.nextLine();
            String[] strs=str.split(" ");
            int key=Integer.parseInt(strs[1]);
            if(strs[0].equals("A")){
                lru.insert(key,key);
            }
            if(strs[0].equals("D")){
                lru.delete(key);
            }
            if(strs[0].equals("Q")){
                lru.query(key);
            }
        }
        Set<Integer> keys = lru.linkedHashMap.keySet();
        List<Integer> list = new ArrayList<>(keys);
        list.sort((o1, o2) -> o1-o2);
        for(int i=0;i<list.size();i++){
            if(i==list.size()-1){
                System.out.print(list.get(i));
            }else {
                System.out.print(list.get(i)+" ");
            }
        }
    }

    public void query(int key) {
        if (!linkedHashMap.containsKey(key)) {
            return;
        }
        int value = linkedHashMap.remove(key);
        linkedHashMap.put(key, value);
    }

    public void insert(int key, int value) {
        if (linkedHashMap.containsKey(key)) {
            linkedHashMap.remove(key);
            linkedHashMap.put(key, value);
            return;
        }
        if (linkedHashMap.size() >= capacity) {
            delete(-1);
        }
        linkedHashMap.put(key, value);
    }
    public void delete(int key){
        if(key==-1){
            int tempKey = linkedHashMap.keySet().iterator().next();
            linkedHashMap.remove(tempKey);
        }
        if(!linkedHashMap.containsKey(key))
            return;
        linkedHashMap.remove(key);
    }
}
