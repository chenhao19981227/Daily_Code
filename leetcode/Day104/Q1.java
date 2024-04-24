package leetcode.Day104;

public class Q1 {
    public int[][] snake(int n){
        int row_flag=-1,col_flag=1;
        boolean flag=true;
        boolean isChange=false;
        int[][] res=new int[n][n];
        int row=0,col=0;
        int cur=1;
        while (row<n&&col<n){
            res[row][col]=cur++;
            if((row==0||col==0||row==n-1||col==n-1)&&flag){
                flag=false;
                row_flag=-row_flag;
                col_flag=-col_flag;
                if(row==0||row==n-1){
                    if(row==0&&col==n-1)
                        row++;
                    else
                        col++;
                }else
                    row++;
            }else {
                row+=row_flag;
                col+=col_flag;
                flag=true;
            }
        }

        return res;
    }
}
