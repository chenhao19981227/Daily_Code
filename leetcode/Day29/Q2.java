package leetcode.Day29;

public class Q2 {

    public int findPeakElement(int[] nums) {
        int left=0,right=nums.length-1;
        while (left<right){
            int mid=(right-left)/2+left;
            if(nums[mid]<nums[mid+1]){
                left=mid+1;
            }else {
                right=mid;
            }
        }
        return left;
    }
}
