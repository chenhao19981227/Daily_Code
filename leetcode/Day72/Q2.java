package leetcode.Day72;

public class Q2 {
    public int maximum(int a, int b) {
        long val=(long)a-(long)b;
        int[] arr=new int[]{a,b};
        return arr[(int) (val>>>63)];
    }
}
