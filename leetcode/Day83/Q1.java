package leetcode.Day83;

public class Q1 {
    public int add(int a, int b) {
        while (b!=0){
            int carry=(a&b)<<1;
            a=a^b;
            b=carry;
        }
        return a;
    }
}
