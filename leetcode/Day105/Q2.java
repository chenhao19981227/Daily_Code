package leetcode.Day105;

public class Q2 {
    public int[] findSquare(int[][] matrix) {
        int n = matrix.length;
        int[][] right = new int[n][n];
        int[][] down = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    right[i][j] = (j == n - 1) ? 1 : right[i][j + 1] + 1;
                    down[i][j] = (i == n - 1) ? 1 : down[i + 1][j] + 1;
                } else {
                    right[i][j] = 0;
                    down[i][j] = 0;
                }
            }
        }

        int maxLen = 0;
        int[] res = new int[3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int size = Math.min(down[i][j], right[i][j]); size > maxLen; size--) {
                    if (right[i + size - 1][j] >= size && down[i][j + size - 1] >= size) {
                        maxLen = size;
                        res[0] = i;
                        res[1] = j;
                        res[2] = size;
                        break;
                    }
                }
            }
        }

        return maxLen == 0 ? new int[0] : res;
    }
}
