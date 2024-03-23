package leetcode.Day72;

import java.util.Arrays;

public class Q1 {
    public int smallestDifference(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        if (m == 1 && n == 1) {
            return Math.abs(a[0] - b[0]);
        }
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;
        int j = 0;
        long res =  Long.MAX_VALUE;
        while (i < m && j < n) {
            if (a[i] == b[j]) {
                return 0;
            }
            long dif = a[i] - b[j];
            res = Math.min(Math.abs(dif), res);
            if (dif > 0) {
                j++;
            } else {
                i++;
            }
        }
        return (int) res;
    }
}
