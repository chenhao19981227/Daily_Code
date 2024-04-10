package leetcode.Day90;

public class Q1 {
    public int trap(int[] height) {
        int len=height.length;
        if(len==0) return 0;
        int[] left=new int[len];
        int[] right=new int[len];
        right[0]=height[0];
        int max=height[0];
        for (int i = 1; i < len; i++) {
            max=Math.max(max,height[i]);
            right[i]=max;
        }
        max=height[len-1];
        left[len-1]=height[len-1];
        for(int i=len-2;i>=0;i--){
            max=Math.max(max,height[i]);
            left[i]=max;
        }
        int res=0;
        for (int i = 0; i < len; i++) {
            res+=Math.min(left[i],right[i])-height[i];
        }
        return Math.max(res,0);
    }
}
