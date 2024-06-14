package leetcode.Day154;

public class Q1 {
    public long maxScore(int[] nums, int x) {
        int len=nums.length;
        long[] dp=new long[]{Integer.MIN_VALUE,Integer.MIN_VALUE};
        dp[nums[0]%2]=nums[0];
        long res=nums[0];
        for (int i = 1; i < nums.length; i++) {
            int flag=nums[i]%2;
            long temp=Math.max(dp[flag]+nums[i],dp[1-flag]-x+nums[i]);
            res=Math.max(temp,res);
            dp[flag]=Math.max(temp,dp[flag]);
        }
        return res;
    }
}
