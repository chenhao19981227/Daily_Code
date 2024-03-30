package leetcode.Day79;

import java.util.*;

public class Q1 {
    public List<List<Integer>> pairSums(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int num=nums[i];
            if(map.containsKey(num)&&map.get(num)!=0){
                List<Integer> temp=new ArrayList<>();
                temp.add(target-num);
                temp.add(num);
                res.add(new ArrayList<>(temp));
                map.put(num,map.get(num)-1);
            }else {
                map.put(target-num,map.getOrDefault(target-num,0)+1);
            }
        }
        return res;
    }
}
