package leetcode.Day88;

public class Q1 {
    public int massage(int[] nums) {
        int len=nums.length;
        if(len==0)return 0;
        int[][] dp=new int[len][2];
        dp[0][0]=nums[0];
        dp[0][1]=0;
        for(int i=1;i<len;i++){
            dp[i][0]=dp[i-1][1]+nums[i];
            dp[i][1]=Math.max(dp[i-1][0],dp[i-1][1]);
        }
        return Math.max(dp[len-1][0],dp[len-1][1]);
    }
}
