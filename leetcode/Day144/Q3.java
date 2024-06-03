package leetcode.Day144;

import java.util.PriorityQueue;

public class Q3 {
    public int maximumProduct(int[] nums, int k) {
        long ans=1,mod=1000000007;
        PriorityQueue<Integer>q=new PriorityQueue<>((a,b)->a-b);
        for(int n:nums) q.offer(n);
        while(k!=0){
            int m=q.poll()+1;
            q.offer(m);
            k--;
        }
        while(!q.isEmpty()){
            ans=ans*q.poll()%mod;
        }
        return (int)(ans%mod);
    }
}
