package leetcode.Day142;

public class Q1 {
    public int distributeCandies(int n, int limit) {
        int ans = 0;
        for (int i = 0; i <= limit; i++) {
            for (int j = 0; j <= limit; j++) {
                if (i + j > n) {
                    break;
                }
                if (n - i - j <= limit) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
