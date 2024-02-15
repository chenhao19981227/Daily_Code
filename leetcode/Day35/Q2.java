package leetcode.Day35;

public class Q2 {
    public int[] countBits(int n) {
        int[] result=new int[n+1];
        for (int i = 0; i <= n; i++) {
            int count=0,num=i;
            while (num>0){
                num&=num-1;
                count++;
            }
            result[i]=count;
        }
        return result;
    }
}
