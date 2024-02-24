package leetcode.Day44;

public class Q2 {
    public void setZeroes(int[][] matrix) {
        int m=matrix.length,n=matrix[0].length;
        int[][] temp=new int[m][n];
        for (int i = 0; i < m; i++) {
            temp[i]=matrix[i].clone();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(temp[i][j]==0){
                    for (int k = 0; k < m; k++) {
                        matrix[k][j]=0;
                    }
                    for (int k = 0; k < n; k++) {
                        matrix[i][k]=0;
                    }
                }
            }
        }
    }
}
