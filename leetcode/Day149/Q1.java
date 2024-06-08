package leetcode.Day149;

import java.util.Arrays;

public class Q1 {
    public int maxOperations(int[] nums) {
        int result = 0, n = nums.length;
        int[][] memo = new int[n][n];
        for(int[] arr: memo) Arrays.fill(arr, -1);

        result = dfs(nums, 2, n-1, nums[0]+nums[1], memo)+1;
        result = Math.max(result, dfs(nums, 0, n-3, nums[n-1]+nums[n-2], memo)+1);
        return Math.max(result, dfs(nums, 1, n-2, nums[0]+nums[n-1], memo)+1);
    }
    // nums[left, right]对应的最大操作数
    public int dfs(int[] nums, int left, int right, int lastSum, int[][] memo){
        // 出口
        if(left>=right) return 0;
        else if(left==right-1 && nums[left]+nums[right]==lastSum) return 1; // 区间只有一对元素

        // 记忆化，避免重复搜索
        if(memo[left][right]!=-1) return memo[left][right];

        int result = 0;
        if(nums[left]+nums[left+1]==lastSum)
            result = dfs(nums, left+2, right, lastSum, memo)+1;
        if(nums[right]+nums[right-1]==lastSum)
            result = Math.max(result, dfs(nums, left, right-2, lastSum, memo)+1);
        if(nums[left]+nums[right]==lastSum)
            result = Math.max(result, dfs(nums, left+1, right-1, lastSum, memo)+1);

        memo[left][right] = result;
        return result;
    }
}
