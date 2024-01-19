package leetcode.Day8;

import java.util.HashMap;
import java.util.Map;

public class Q2 {
    public int maxOperations(int[] nums, int k) {
        int len=nums.length;
        Map<Integer,Integer> map=new HashMap<>();
        int result=0;
        for (int i = 0; i < len; i++) {
            int num=nums[i];
            int need=k-num;
            if(map.containsKey(need)){
                result++;
                if(map.get(need)>1){
                    map.put(need,map.get(need)-1);
                }else {
                    map.remove(need);
                }
            }else{
                map.put(num,map.getOrDefault(num,0)+1);
            }
        }
        return result;
    }
}
