package leetcode.Day143;

public class Q1 {
    public int distributeCandies(int[] candyType) {
        int n=candyType.length;
        int[] mark=new int[2*100000+1];
        int res=0;
        for (int num : candyType) {
            if(mark[num+100000]++==0)
                res++;
        }
        return Math.min(res,n/2);
    }
}
