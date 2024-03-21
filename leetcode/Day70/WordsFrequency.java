package leetcode.Day70;

import java.util.HashMap;
import java.util.Map;

class WordsFrequency {
    Map<String,Integer> map;
    public WordsFrequency(String[] book) {
        map=new HashMap<>();
        for (String word : book) {
            map.put(word,map.getOrDefault(word,0)+1);
        }
    }
    
    public int get(String word) {
        return map.getOrDefault(word,0);
    }
}