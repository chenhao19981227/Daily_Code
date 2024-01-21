package leetcode.Day10;

public class Q1 {
    public int longestOnes(int[] nums, int k) {
        int len=nums.length;
        int left=0,right=0;
        int max=0;
        int num0=0;
        while(right<len){
            while(right<len&&num0<=k){
                if(nums[right]==0)num0++;
                right++;
            }
            max=Math.max(max,right-left-num0);
            while(num0>k){
                if(nums[left]==0)num0--;
                left++;
            }
        }
        return Math.min(max+k,len);
    }
}
