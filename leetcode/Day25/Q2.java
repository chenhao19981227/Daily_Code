package leetcode.Day25;

public class Q2 {
    public int orangesRotting(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        int[][] directions = {{0, 1, 0, -1}, {1, 0, -1, 0}};
        int good=0;
        for (int[] row : grid) {
            for (int i : row) {
                if(i==1)good++;
            }
        }
        boolean[][] isVisited=new boolean[m][n];
        int time=0;
        boolean isChange=true;
        while (isChange&&good!=0){
            isChange=false;
            int[][] temp=new int[m][n];
            for (int i = 0; i < m; i++) {
                temp[i]=grid[i].clone();
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(temp[i][j]==2&&!isVisited[i][j]){
                        isVisited[i][j]=true;
                        for (int k = 0; k < 4; k++) {
                            int x=directions[0][k]+i;
                            int y=directions[1][k]+j;
                            if(x>=0&&x<m&&y>=0&&y<n&&grid[x][y]==1){
                                grid[x][y]=2;
                                isChange=true;
                                good--;
                            }
                        }
                    }
                }
            }
            time++;
        }
        return good==0?time:-1;
    }

}
