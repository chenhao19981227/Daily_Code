package leetcode.Day60;

public class Q1 {
    public int findMagicIndex(int[] nums) {
        for(int i=0;i<nums.length;i++){
            if(nums[i]==i)return i;
        }
        return -1;
    }
}
