package leetcode.Day26;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Q1 {
    public int findKthLargest(int[] nums, int k) {
        int len=nums.length;
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int num : nums) {
            priorityQueue.offer(num);
        }
        for (int i = 0; i < k - 1; i++) {
            priorityQueue.poll();
        }
        return priorityQueue.peek();
    }
}
