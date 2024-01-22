package leetcode.Day11;

public class Q1 {
    public int largestAltitude(int[] gain) {
        int max=0;
        int pre=0,cur=0;
        for (int i = 0; i < gain.length; i++) {
            cur=gain[i]+pre;
            max=Math.max(cur,max);
            pre=cur;
        }
        return max;
    }
}
