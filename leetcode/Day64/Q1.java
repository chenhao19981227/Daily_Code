package leetcode.Day64;

public class Q1 {
    private static final int MOD=1000000007;
    int[] coins={1,5,10,25};
    public int waysToChange(int n) {
        int[] dp=new int[n+1];
        dp[0]=1;
        for (int coin : coins) {
            for (int i = coin; i <= n ; i++) {
                dp[i]=(dp[i-coin]+dp[i])%MOD;
            }
        }
        return dp[n];
    }
}
