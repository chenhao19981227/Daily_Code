package leetcode.Day15;

import java.util.ArrayDeque;
import java.util.Queue;

public class RecentCounter {
    Queue<Integer> queue;
    public RecentCounter() {
        queue = new ArrayDeque<Integer>();
    }

    public int ping(int t) {
        queue.offer(t);
        while (!queue.isEmpty()&&queue.peek()<t-3000)queue.poll();
        return queue.size();
    }
}
