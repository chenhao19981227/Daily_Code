package leetcode.Day37;

import java.util.Arrays;

public class Q1 {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0]);
        int result=0;
        int right=intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur=intervals[i];
            if(cur[0]<right){
                result++;
                right=Math.min(right,cur[1]);
            }else {
                right=cur[1];
            }
        }
        return result;
    }
}
