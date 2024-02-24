package leetcode.Day44;

public class Q1 {
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        int start=0,end=n-1;
        while (start<end){
            for(int i=start;i<end;i++){
                int temp=matrix[i][end];
                matrix[i][end]=matrix[start][i];
                matrix[start][i]=matrix[end-i+start][start];
                matrix[end-i+start][start]=matrix[end][end-i+start];
                matrix[end][end-i+start]=temp;
            }
            start++;
            end--;
        }
    }
}
