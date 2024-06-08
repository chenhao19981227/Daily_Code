package leetcode.Day149;

import java.util.ArrayList;
import java.util.List;

public class Q2 {
    public List<String> summaryRanges(int[] nums) {
        List<String> res=new ArrayList<>();
        int n=nums.length;
        if(n==0) return res;
        int i=0;
        StringBuilder s;
        while(i<n){
            s=new StringBuilder();
            s.append(nums[i]);
            if(i<n-1&&nums[i+1]==nums[i]+1){
                while(i<n-1&&nums[i]+1==nums[i+1]) i++;
                s.append("->");s.append(nums[i]);
            }
            res.add(s.toString());
            i++;
        }
        return res;
    }
}
