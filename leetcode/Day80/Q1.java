package leetcode.Day80;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q1 {
    public int calculate(String s) {
        Deque<Integer> stack=new ArrayDeque<>();
        char op='+';
        int num=0;
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if(Character.isDigit(c)){
                num=num*10+(c-'0');
            }
            if(!Character.isDigit(c)&&c!=' '||i==s.length()-1){
                if(op=='+')
                    stack.push(num);
                else if(op=='-')
                    stack.push(-num);
                else if(op=='*')
                    stack.push(stack.pop()*num);
                else
                    stack.push(stack.pop()/num);
                num=0;
                op=c;
            }
        }
        int res=0;
        while (!stack.isEmpty())
            res+=stack.pop();
        return res;
    }

}
