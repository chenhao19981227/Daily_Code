package leetcode.Day38;

import java.util.ArrayDeque;
import java.util.Deque;

class StockSpanner {
    Deque<int[]> stack;
    int index;
    public StockSpanner() {
        stack=new ArrayDeque<>();
        stack.push(new int[]{-1,Integer.MAX_VALUE});
        index=-1;
    }
    
    public int next(int price) {
        index++;
        while (stack.peek()[1]<=price){
            stack.pop();
        }
        int gap=index-stack.peek()[0];
        stack.push(new int[]{index,price});
        return gap;
    }
}