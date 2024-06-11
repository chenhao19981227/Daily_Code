package leetcode.Day151;

import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k = sc.nextInt(),m=sc.nextInt(),n=sc.nextInt();
        if(m+n>k){
            System.out.println(0);
            return;
        }
        int[][] dp=new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            dp[i][0]=1;
        }
        for (int i = 1; i <=n; i++) {
            dp[0][i]=1;
        }
        for (int i = 1; i <=m; i++) {
            for (int j = 1; j <=n; j++) {
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        System.out.println(dp[m][n]);
    }
}
