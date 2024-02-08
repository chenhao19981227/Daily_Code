package leetcode.Day28;

import java.util.Arrays;

public class Q2 {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int len=spells.length;
        int[] res=new int[len];
        for (int i = 0; i < len; i++) {
            if((long) spells[i] *potions[potions.length-1]<success)continue;
            int min=binarySearch(potions,spells[i],success);
            res[i]=potions.length-min;
        }
        return res;
    }
    public int binarySearch(int[] potions,int spell,long success){
        int len=potions.length;
        int left=0,right=len-1;
        while (left<=right){
            if(left==right){
                if((long)spell*potions[left]>=success){
                    return left;
                }else {
                    return left+1;
                }
            }
            int mid=(right-left)/2+left;
            int potion=potions[mid];
            long mul= (long) spell *potion;
            if(mul<success){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Q2 test=new Q2();
        test.successfulPairs(new int[]{5,1,3},new int[]{1,2,3,4,5},7);
    }
}
