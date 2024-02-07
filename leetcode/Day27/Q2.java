package leetcode.Day27;

import java.util.PriorityQueue;

public class Q2 {
    public long totalCost(int[] costs, int k, int candidates) {
        int len=costs.length;
        long res=0L;
        PriorityQueue<int[]> priorityQueue=new PriorityQueue<>(
                (arr1,arr2)-> arr1[0]==arr2[0]?arr1[1]-arr2[1]:arr1[0]-arr2[0]
        );
        for (int i = 0; i < len; i++) {
            if(i<candidates||i>=len-candidates){
                priorityQueue.offer(new int[]{costs[i],i<candidates?0:1});
            }
        }
        int left=candidates-1,right=len-candidates;
        for (int i = 0; i < k; i++) {
            int[] cheapest=priorityQueue.poll();
            res+=cheapest[0];
            if(left+1>=right)continue;
            if(cheapest[1]==0){
                left++;
                priorityQueue.offer(new int[]{costs[left],0});
            }else {
                right--;
                priorityQueue.offer(new int[]{costs[right],1});
            }
        }
        return res;
    }
}
