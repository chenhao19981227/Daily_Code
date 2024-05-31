package leetcode.Day141;

public class Q1 {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n=grid.length;
        int[] mark=new int[n*n];
        for (int[] ints : grid) {
            for (int num : ints) {
                mark[num-1]++;
            }
        }
        int[] res=new int[2];
        for (int i = 0; i < mark.length; i++) {
            if(mark[i]==2)
                res[0]=i+1;
            if(mark[i]==0)
                res[1]=i+1;
        }
        return res;
    }
}
