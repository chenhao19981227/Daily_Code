package leetcode.Day100;

import java.util.HashSet;
import java.util.PriorityQueue;

public class Q1 {
    private static final int[] BASE={3,5,7};
    public int getKthMagicNumber(int k) {
        if(k==1) return 1;
        PriorityQueue<Long> pail=new PriorityQueue<>();
        HashSet<Long> set=new HashSet<>();
        pail.offer(1L);
        set.add(1L);
        int res=0;
        for (int i = 0; i < k; i++) {
            long cur=pail.poll();
            res=(int) cur;
            for (int num : BASE) {
                long next=num*cur;
                if(set.add(next)){
                    pail.offer(next);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Q1 test=new Q1();
        System.out.println(test.getKthMagicNumber(5));
    }
}
