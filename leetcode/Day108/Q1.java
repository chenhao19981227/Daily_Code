package leetcode.Day108;

public class Q1 {
    public int[] getMaxMatrix(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int[][] preSum=new int[m][n];
        for (int i = 0; i < n; i++) {
            preSum[0][i]=matrix[0][i];
            for(int j=1;j<m;j++){
                preSum[j][i]=preSum[j-1][i]+matrix[j][i];
            }
        }
        int max=Integer.MIN_VALUE;
        int[] res=new int[4];
        for(int row1=0;row1<m;row1++){
            for(int row2=row1;row2<m;row2++){
                int start_col=0,curSum=0;
                for(int col=0;col<n;col++){
                    int colSum=preSum[row2][col]-(row1==0?0:preSum[row1-1][col]);
                    if(curSum>0){
                        curSum+=colSum;
                    }else {
                        start_col=col;
                        curSum=colSum;
                    }
                    if(curSum>max){
                        max=curSum;
                        res=new int[]{row1,start_col,row2,col};
                    }
                }
            }
        }
        return res;
    }
}
