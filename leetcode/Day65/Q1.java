package leetcode.Day65;

import java.util.Arrays;

public class Q1 {
    public int pileBox(int[][] boxes) {
        Arrays.sort(boxes, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            else if (a[1] != b[1]) return a[1] - b[1];
            else return a[2] - b[2];
        });
        int[] dp = new int[boxes.length];
        for (int i = 0; i < boxes.length; i++) {
            dp[i] = boxes[i][2];
        }
        for (int i = 1; i < boxes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (boxes[i][0] > boxes[j][0] && boxes[i][1] > boxes[j][1] && boxes[i][2] > boxes[j][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + boxes[i][2]);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
