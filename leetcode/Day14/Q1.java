package leetcode.Day14;

import java.util.ArrayDeque;
import java.util.Deque;

class Q1 {
    public String removeStars(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(!stack.isEmpty()&&c=='*'&&stack.peekFirst()!='*'){
                stack.pollFirst();
            }else{
                stack.addFirst(c);
            }
        }
        StringBuffer sb=new StringBuffer();
        while(!stack.isEmpty()){
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
}
