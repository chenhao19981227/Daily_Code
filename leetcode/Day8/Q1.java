package leetcode.Day8;

public class Q1 {
    public void moveZeroes(int[] nums) {
        int len=nums.length;
        int left=0,right=0;
        while (right<len){
            if(nums[right]!=0){
                int temp= nums[left];
                nums[left]=nums[right];
                nums[right]=temp;
                left++;
            }
            right++;
        }
    }
}
