package leetcode.Day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q2 {
    public int equalPairs(int[][] grid) {
        int n=grid.length;
        int res=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res+=compare(grid,i,j);
            }
        }
        return res;
    }
    private int compare(int[][] grid, int row, int col) {
        int n=grid.length;
        for (int i = 0; i < n; i++) {
            if(grid[row][i]!=grid[i][col])return 0;
        }
        return 1;
    }
}
