package leetcode.Day148;

public class Q1 {
    public int maxOperations(int[] nums) {
        int n = nums.length;
        int ans = 1, score = nums[0] + nums[1];

        for (int i = 2; i + 1 < n; i += 2) {
            if (nums[i] + nums[i + 1] != score) {
                break;
            }
            ans++;
        }

        return ans;
    }
}
