package leetcode.Day28;

import java.util.Arrays;

public class Q2 {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int len=spells.length;
        int[] res=new int[len];
        for (int i = 0; i < len; i++) {
            int min=binarySearch(potions,spells[i],success);
            res[i]=potions.length-min;
        }
        return res;
    }
    public int binarySearch(int[] potions,int spell,long success){
        int len=potions.length;
        int left=0,right=len-1;
        int res=right+1;
        while (left<=right){
            int mid=(right-left)/2+left;
            if((long) potions[mid] *spell>=success){
                res=mid;
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Q2 test=new Q2();
        test.successfulPairs(new int[]{5,1,3},new int[]{1,2,3,4,5},7);
    }
}
