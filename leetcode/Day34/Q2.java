package leetcode.Day34;

public class Q2 {
    public int maxProfit(int[] prices, int fee) {
        int len=prices.length;
        int[][] dp=new int[len][2];
        dp[0][1]=-prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]-fee);
            dp[i][1]=Math.max(dp[i-1][0]-prices[i],dp[i-1][1]);
        }
        return dp[len-1][0];
    }
}
