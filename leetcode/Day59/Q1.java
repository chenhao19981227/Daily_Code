package leetcode.Day59;

public class Q1 {
    public int waysToStep(int n) {
        if(n <= 2) return n;
        if (n == 3) return 4;
        int[] dp=new int[n];
        dp[0]=1;
        dp[1]=2;
        dp[2]=4;
        for (int i = 3; i < n; i++) {
            dp[i]=((dp[i-1]+dp[i-2])%1000000007+dp[i-3])%1000000007;
            dp[i]%=1000000007;
        }
        return dp[n-1];
    }
}
