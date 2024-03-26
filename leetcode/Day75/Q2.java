package leetcode.Day75;

public class Q2 {
    public int maxSubArray(int[] nums) {
        int preSum=0,max=Integer.MIN_VALUE;
        for (int num : nums) {
            preSum=Math.max(preSum+num,num);
            max=Math.max(max,preSum);
        }
        return max;
    }
}
