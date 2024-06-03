package leetcode.Day144;

public class Q2 {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int temp = 0;
        while (candies > 0) {
            int t = ((2 * temp + 1) * num_people + 1) * num_people / 2;
            if (candies >= t) {
                candies -= t;
                temp++;
            } else {
                break;
            }
        }
        // 得到轮数
        for (int k = 0; k < num_people && temp != 0; k++) {
            ans[k] = (2 * k + 2 + num_people * (temp - 1)) * temp / 2;
        }
        // 剩余的不能走完的轮数
        for (int k = 0; k < num_people; k++) {
            if (candies > 0) {
                int t = Math.min(k + 1 + temp * num_people, candies);
                ans[k] += t;
                candies -= t;
            }
        }
        return ans;
    }
}
