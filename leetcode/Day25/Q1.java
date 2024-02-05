package leetcode.Day25;

import java.util.LinkedList;
import java.util.Queue;

public class Q1 {
    public int nearestExit(char[][] maze, int[] entrance) {
        int m=maze.length,n=maze[0].length;
        int[][] directions = {{0, 1, 0, -1}, {1, 0, -1, 0}};
        Queue<int[]> queue=new LinkedList<>();
        queue.offer(new int[]{entrance[0],entrance[1],0});
        maze[entrance[0]][entrance[1]]='+';
        while (!queue.isEmpty()){
            int[] curPosition=queue.poll();
            int curX=curPosition[0],curY=curPosition[1],step=curPosition[2];
            for (int i = 0; i < 4; i++) {
                int nextX=curX+directions[0][i];
                int nextY=curY+directions[1][i];
                if(nextX>=0&&nextX<m&&nextY>=0&&nextY<n&&maze[nextX][nextY]=='.'){
                    if(nextX==0||nextX==m-1||nextY==0||nextY==n-1){
                        return step+1;
                    }
                    queue.offer(new int[]{nextX,nextY,step+1});
                    maze[nextX][nextY]='+';
                }
            }
        }
        return -1;
    }
}
