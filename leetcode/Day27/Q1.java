package leetcode.Day27;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q1 {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int len = nums1.length;
        Integer[] index= new Integer[len];
        for (int i = 0; i < len; i++) {
            index[i]=i;
        }
        Arrays.sort(index, (i,j) -> nums2[j]-nums2[i]);
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>();
        long res=0;
        long sum=0;
        for (int i = 0; i < k; i++) {
            sum+=nums1[index[i]];
            priorityQueue.offer(nums1[index[i]]);
        }
        res=nums2[index[k-1]]*sum;
        for (int i = k; i < len; i++) {
            Integer min = priorityQueue.peek();
            int cur=nums1[index[i]];
            if(cur>min){
                priorityQueue.poll();
                priorityQueue.offer(cur);
                sum=sum-min+cur;
                res=Math.max(res,sum*nums2[index[i]]);
            }
        }
        return res;
    }
}
