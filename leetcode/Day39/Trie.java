package leetcode.Day39;

import java.util.HashMap;
import java.util.Map;

class Trie {
    Map<String ,Integer> map;
    public Trie() {
        map=new HashMap<>();
    }
    
    public void insert(String word) {
        map.put(word,1);
    }
    
    public boolean search(String word) {
        return map.containsKey(word);
    }
    
    public boolean startsWith(String prefix) {
        for (String word : map.keySet()) {
            if(word.startsWith(prefix))return true;
        }
        return false;
    }
}