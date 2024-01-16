package leetcode.Day5;

public class Q2 {
    public boolean increasingTriplet(int[] nums) {
        int len=nums.length;
        int a=nums[0],b=Integer.MAX_VALUE;
        for(int i=1;i<len;i++){
            int num=nums[i];
            if(num>b)return true;
            if(num>a&&num<b)b=num;
            if(num<a)a=num;
        }
        return false;
    }
}
