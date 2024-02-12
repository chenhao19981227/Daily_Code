package leetcode.Day32;

public class Q1 {
    public int minCostClimbingStairs(int[] cost) {
        int len=cost.length;
        if(len<=2)return Math.min(cost[0],cost[1]);
        int[] dp=new int[len+1];
        for(int i=2;i<=len;i++){
            dp[i]=Math.min(cost[i-1]+dp[i-1],cost[i-2]+dp[i-2]);
        }
        return dp[len];
    }
}
