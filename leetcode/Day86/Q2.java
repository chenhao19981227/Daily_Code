package leetcode.Day86;

import java.util.PriorityQueue;

public class Q2 {
    public int[] smallestK(int[] arr, int k) {
        int[] res=new int[k];
        if(k==0)return res;
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>((o1, o2) -> o2-o1);
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(arr[i]);
        }
        for(int i=k;i<arr.length;i++){
            if(arr[i]<priorityQueue.peek()){
                priorityQueue.poll();
                priorityQueue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            res[i]=priorityQueue.poll();
        }
        return res;
    }
}
