package leetcode.Day107;

import java.util.PriorityQueue;

class MedianFinder {
    private PriorityQueue<Integer> left_part;
    private PriorityQueue<Integer> right_part;
    public MedianFinder() {
        left_part=new PriorityQueue<>(((o1, o2) -> o2-o1));
        right_part=new PriorityQueue<>();
    }
    public void addNum(int num) {
        left_part.offer(num);
        right_part.offer(left_part.poll());
        if(left_part.size()<right_part.size())
            left_part.offer(right_part.poll());
    }
    public double findMedian() {
        return left_part.size()==right_part.size()?(left_part.peek()+right_part.peek())/2.0:left_part.peek();
    }
}