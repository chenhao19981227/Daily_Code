package leetcode.Day10;

public class Q2 {
    public int longestSubarray(int[] nums){
        int len=nums.length;
        int left=0,right=0;
        int max=0;
        int num0=0;
        while(right<len){
            while(right<len&&num0<=1){
                if(nums[right]==0)num0++;
                right++;
            }
            max=Math.max(max,right-left-num0);
            while(num0>1){
                if(nums[left]==0)num0--;
                left++;
            }
        }
        return Math.min(max,len-1);
    }
}
