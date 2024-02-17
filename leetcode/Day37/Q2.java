package leetcode.Day37;

import java.util.Arrays;

public class Q2 {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (o1, o2) -> o1[0]==o2[0]?Integer.compare(o1[1],o2[1]):Integer.compare(o1[0],o2[0]));
        int right=points[0][1];
        int result=1;
        for (int i = 1; i < points.length; i++) {
            int[] cur=points[i];
            if(cur[0]>right){
                result++;
                right=cur[1];
            }else {
                right=Math.min(right,cur[1]);
            }
        }
        return result;
    }
}
