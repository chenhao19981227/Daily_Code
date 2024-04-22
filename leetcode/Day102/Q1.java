package leetcode.Day102;

public class Q1 {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;
        long sum = (long)n * (n + 1) / 2;
        long squareSum = (long)n * (n + 1) * (2L * n + 1) / 6;

        for (int num : nums) {
            sum -= num;
            squareSum -= (long)num * num;
        }

        double sqrt = Math.sqrt(2 * squareSum - sum * sum);
        int a = (int)((sum + sqrt) / 2);
        int b = (int)((sum - sqrt) / 2);

        return new int[]{a, b};
    }
}
