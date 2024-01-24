package leetcode.Day13;

import java.util.HashMap;
import java.util.Map;

public class Q1 {
    public boolean uniqueOccurrences(int[] arr) {
        int[] mark=new int[2001];
        for (int num : arr) {
            mark[num+1000]++;
        }
        Map<Integer,Integer> map=new HashMap<>();
        for (int count : mark) {
            if(count==0)continue;
            if(map.containsKey(count))return false;
            map.put(count,1);
        }
        return true;
    }
}
