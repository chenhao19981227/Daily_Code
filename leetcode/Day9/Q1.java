package leetcode.Day9;

public class Q1 {
    public double findMaxAverage(int[] nums, int k) {
        int len=nums.length;
        int sum=0;
        for (int i = 0; i < k; i++) {
            sum+=nums[i];
        }
        int max=sum;
        for(int i=k;i<len;i++){
            sum-=nums[i-k];
            sum+=nums[i];
            max=Math.max(max,sum);
        }
        return 1.0*max/k;
    }
}
