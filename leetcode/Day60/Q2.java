package leetcode.Day60;

import java.util.ArrayList;
import java.util.List;

public class Q2 {
    static List<List<Integer>> result;
    static List<Integer> list;
    public List<List<Integer>> subsets(int[] nums) {
        result=new ArrayList<>();
        list=new ArrayList<>();
        dfs(nums,0);
        return result;
    }
    private static void dfs(int[] nums,int index){
        if(index==nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[index]);
        dfs(nums,index+1);
        list.remove(list.size()-1);
        dfs(nums,index+1);
    }
}
