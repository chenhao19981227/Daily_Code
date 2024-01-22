package leetcode.Day11;

public class Q2 {
    public int pivotIndex(int[] nums) {
        int len=nums.length;
        int[] preSum=new int[len];
        preSum[0]=nums[0];
        for (int i = 1; i < len; i++) {
            preSum[i]=preSum[i-1]+nums[i];
        }
        int sum=preSum[len-1];
        for (int i = 0; i < len; i++) {
            int tempSum=sum-nums[i];
            int leftSum=preSum[i]-nums[i];
            if(tempSum%2!=0)continue;
            if(leftSum==tempSum/2){
                return i;
            }
        }
        return -1;
    }
}
