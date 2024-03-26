package leetcode.Day75;

public class Q1 {
    public int[] subSort(int[] array) {
        if(array.length==0)return new int[]{-1,-1};
        int left=-1,right=-1;
        int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if(array[i]>=max)max=array[i];
            else right=i;
        }
        if(right==-1) return new int[]{-1,-1};
        for(int i=array.length-1;i>=0;i--){
            if(array[i]<=min)min=array[i];
            else left=i;
        }
        return new int[]{left,right};
    }
}
