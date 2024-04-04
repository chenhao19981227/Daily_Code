package leetcode.Day84;

import java.util.HashMap;
import java.util.Map;

public class Q1 {
    public String[] findLongestSubarray(String[] array) {
        int len=array.length;
        int num=0,letter=0;
        int max=0;
        int left=0,right=0;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        for (int i = 0; i < len; i++) {
             char c=array[i].charAt(0);
             if(Character.isDigit(c))
                 num++;
             else
                 letter++;
             int gap=num-letter;
             if(!map.containsKey(gap)){
                 map.put(gap,i);
             }else {
                 int temp=i-map.get(gap);
                 if(max<temp){
                     max=temp;
                     left=map.get(gap)+1;
                     right=i;
                 }
             }
        }
        if(max==0)
            return new String[]{};
        String[] res=new String[right-left+1];
        System.arraycopy(array, left, res, 0, right + 1 - left);
        return res;
    }
}
