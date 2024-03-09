package leetcode.Day58;

public class Q1 {
    public int insertBits(int N, int M, int i, int j) {
        //left in (,j+1];middle in [i,j];right in [i-1,0];
        int left = N>>j>>1;
        left = left<<j<<1;
        int middle = M<<i;
        int right = N&((1<<i)-1);
        return left | middle | right;
    }
}
