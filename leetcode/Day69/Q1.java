package leetcode.Day69;

public class Q1 {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if(i%2==0){
                if(nums[i]<nums[i-1]) swap(nums,i);
            }else {
                if(nums[i]>nums[i-1]) swap(nums,i);
            }
        }
    }

    private void swap(int[] nums, int i) {
        int temp=nums[i];
        nums[i]=nums[i-1];
        nums[i-1]=temp;
    }
}
