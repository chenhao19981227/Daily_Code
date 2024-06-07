package leetcode.Day148;

public class Q2 {
    public int maxDistance(int[] nums1, int[] nums2) {
        int left = 0;
        int right = 0;
        int maxDis = 0;
        while (left < nums1.length && right < nums2.length) {
            if (nums1[left] <= nums2[right]) {
                maxDis = Math.max(maxDis, right++ - left);
            } else {
                left++;
            }
        }
        return maxDis;
    }
}
