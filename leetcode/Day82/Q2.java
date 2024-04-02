package leetcode.Day82;

public class Q2 {
    public int[] findClosedNumbers(int num) {
        int mx = nextOne(num), mi = ~nextOne(~num);
        return new int[]{mx > 0 ? mx : -1, mi > 0 ? mi : -1};
    }
    public int nextOne(int x){
        long lowbit = x & (-x);
        long toZero = x + lowbit;
        return (int)((x & ~toZero) / lowbit >> 1 | toZero);
    }
}
