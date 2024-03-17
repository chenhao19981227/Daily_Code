package leetcode.Day66;

import java.util.*;

public class Q2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result=new ArrayList<>();
        Map<String,List<String>> map=new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String s = Arrays.toString(charArray);
            if(map.containsKey(s)){
                map.get(s).add(str);
            }else {
                List<String> list=new ArrayList<>();
                list.add(str);
                map.put(s,list);
            }
        }
        return new ArrayList<List<String>>(map.values());
    }
}
