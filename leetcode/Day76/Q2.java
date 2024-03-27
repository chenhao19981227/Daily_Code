package leetcode.Day76;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q2 {
    static List<Integer> list;
    static int count=0;
    public int[] pondSizes(int[][] land) {
        int m=land.length,n=land[0].length;
        boolean[][] mark=new boolean[m][n];
        list=new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i,j,m,n,land,mark,true);
            }
        }
        int[] res = list.stream().mapToInt(Integer::valueOf).toArray();
        Arrays.sort(res);
        return res;
    }

    private void dfs(int x, int y, int m, int n, int[][] land, boolean[][] mark,boolean isStart) {
        if(x<0||x>=m||y<0||y>=n||mark[x][y]||land[x][y]!=0){
            return;
        }
        mark[x][y]=true;
        count++;
        dfs(x+1,y,m,n,land,mark,false);
        dfs(x-1,y,m,n,land,mark,false);
        dfs(x,y+1,m,n,land,mark,false);
        dfs(x,y-1,m,n,land,mark,false);
        dfs(x+1,y+1,m,n,land,mark,false);
        dfs(x+1,y-1,m,n,land,mark,false);
        dfs(x-1,y+1,m,n,land,mark,false);
        dfs(x-1,y-1,m,n,land,mark,false);
        if(isStart){
            list.add(count);
            count=0;
        }
    }
}
