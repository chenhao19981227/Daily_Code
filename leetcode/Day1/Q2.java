package leetcode.Day1;

public class Q2 {
    // 热题100 第55 跳跃游戏 贪心算法
    public boolean canJump(int[] nums) {
        int len=nums.length;
        int far=0;//记录能跳到的最远距离
        for (int i = 0; i < len; i++) {
            if(i<=far){
                far=Math.max(far,nums[i]+i);//每次都更新far
                if(far>=len-1)return true;
            }else {
                return false;
            }
        }
        return false;
    }
}
