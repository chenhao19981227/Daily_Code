package leetcode.Day59;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q2 {
    static List<List<Integer>> result;
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        result=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        int m=obstacleGrid.length-1,n=obstacleGrid[0].length-1;
        dfs(obstacleGrid,0,0,m,n);
        return result;
    }

    private boolean dfs(int[][] obstacleGrid, int x, int y, int targetX, int targetY) {
        if(x>=obstacleGrid.length||y>=obstacleGrid[0].length||obstacleGrid[x][y]>=1) return false;
        result.add(Arrays.asList(x,y));
        if(x==targetX&&y==targetY){
            return true;
        }
        obstacleGrid[x][y]=2;
        if(dfs(obstacleGrid,x+1,y,targetX,targetY)||dfs(obstacleGrid,x,y+1,targetX,targetY)){
            return true;
        }
        result.remove(result.size()-1);
        return false;
    }
}
