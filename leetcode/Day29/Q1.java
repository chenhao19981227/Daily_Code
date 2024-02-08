package leetcode.Day29;

public class Q1 {
    public int findPeakElement(int[] nums) {
        int maxIndex=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>nums[maxIndex]){
                maxIndex=i;
            }
        }
        return maxIndex;
    }
}
