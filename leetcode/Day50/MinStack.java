package leetcode.Day50;

import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {

    /** initialize your data structure here. */
    Deque<Integer> stack;
    Deque<Integer> minStack;
    int min;
    public MinStack() {
        stack=new ArrayDeque<>();
        minStack=new ArrayDeque<>();
        min=Integer.MAX_VALUE;
        minStack.push(min);
    }
    
    public void push(int x) {
        stack.push(x);
        if(x<min)min=x;
        minStack.push(min);
    }

    public void pop() {
        if(!stack.isEmpty()){
            stack.pop();
            minStack.pop();
            min=minStack.peek();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}