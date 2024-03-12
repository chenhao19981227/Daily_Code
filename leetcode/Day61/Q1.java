package leetcode.Day61;

public class Q1 {
    public int multiply(int A, int B) {
        return B==0?0:multiply(A<<1,B>>1)+((B&1)==0?0:A);
    }
}
