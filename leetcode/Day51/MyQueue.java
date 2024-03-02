package leetcode.Day51;

import java.util.ArrayDeque;
import java.util.Deque;

class MyQueue {
    Deque<Integer> stack1;
    Deque<Integer> stack2;
    public MyQueue() {
        stack1=new ArrayDeque<>();
        stack2=new ArrayDeque<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(stack1.isEmpty()&&stack2.isEmpty())return -1;
        if(stack2.isEmpty()) move();
        return stack2.pop();
    }

    private void move() {
        while (!stack1.isEmpty()){
           stack2.push(stack1.pop());
        }
    }

    /** Get the front element. */
    public int peek() {
        if(stack1.isEmpty()&&stack2.isEmpty())return -1;
        if(stack2.isEmpty()) move();
        return stack2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty()&&stack2.isEmpty();
    }
}
