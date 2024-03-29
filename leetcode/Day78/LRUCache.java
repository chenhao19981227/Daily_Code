package leetcode.Day78;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    class LRUNode{
        int key;
        int value;
        LRUNode pre;
        LRUNode next;
        public LRUNode(){}
        public LRUNode(int key,int value){
            this.key=key;
            this.value=value;
        }
    }
    Map<Integer,LRUNode> map;
    int capacity;
    int size;
    LRUNode head,tail;
    public LRUCache(int capacity) {
        map=new HashMap<>();
        this.capacity=capacity;
        head=new LRUNode();
        tail=new LRUNode();
        head.next=tail;
        tail.pre=head;
    }

    public int get(int key) {
        LRUNode node=map.get(key);
        if(node==null){
            return -1;
        }
        moveToHead(node);
        return node.value;
    }
    public void moveToHead(LRUNode node){
        removeNode(node);
        addToHead(node);
    }

    public void put(int key, int value) {
        LRUNode node=map.get(key);
        if(node!=null){
            node.value=value;
            moveToHead(node);
        }else {
            node=new LRUNode(key,value);
            map.put(key,node);
            size++;
            addToHead(node);
            if(size>capacity){
                LRUNode tail=removeTail();
                map.remove(tail.key);
                size--;
            }
        }
    }

    private void addToHead(LRUNode node) {
        node.pre=head;
        node.next=head.next;
        head.next.pre=node;
        head.next=node;
    }

    private LRUNode removeTail() {
        LRUNode node=tail.pre;
        removeNode(node);
        return node;
    }

    private void removeNode(LRUNode node) {
        node.next.pre=node.pre;
        node.pre.next=node.next;
    }
}