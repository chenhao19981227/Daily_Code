package leetcode.Day84;

public class Q2 {
    public int majorityElement(int[] nums) {
        if(nums.length==0)return -1;
        int count=1;
        int cur=nums[0];
        for(int i=1;i< nums.length;i++){
            if(nums[i]==cur)
                count++;
            else
                count--;
            if(count<0){
                cur=nums[i];
                count=1;
            }
        }
        count=0;
        for (int num : nums) {
            if(num==cur)count++;
        }
        return count*2>nums.length?cur:-1;
    }
}
