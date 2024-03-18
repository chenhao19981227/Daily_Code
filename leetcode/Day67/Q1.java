package leetcode.Day67;

public class Q1 {
    public int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==target) return i;
        }
        return -1;
    }
}
