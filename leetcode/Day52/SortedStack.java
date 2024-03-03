package leetcode.Day52;

import java.util.ArrayDeque;
import java.util.Deque;

class SortedStack {
    Deque<Integer> stack;
    Deque<Integer> temp;
    public SortedStack() {
        stack=new ArrayDeque<>();
        temp=new ArrayDeque<>();
    }
    
    public void push(int val) {
        while (!stack.isEmpty()&&stack.peek()<val){
            temp.push(stack.pop());
        }
        stack.push(val);
        while (!temp.isEmpty())
            stack.push(temp.pop());
    }

    
    public void pop() {
        if(!isEmpty())
            stack.pop();
    }
    
    public int peek() {
        return stack.isEmpty()?-1: stack.peek();
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}