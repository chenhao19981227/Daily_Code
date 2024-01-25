package leetcode.Day14;

import java.util.ArrayDeque;
import java.util.Deque;

class Q2 {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack=new ArrayDeque<>();
        boolean flag=true;
        for(int i=0;i<asteroids.length;i++){
            int plain=asteroids[i];
            if(plain<0){
                while(!stack.isEmpty()&&stack.peekFirst()>0){
                    if(stack.peekFirst()+plain<0){
                        stack.pollFirst();
                    }else if(stack.peekFirst()+plain==0){
                        stack.pollFirst();
                        flag=false;
                        break;
                    }else{
                        flag=false;
                        break;
                    }
                }
                if(flag)stack.addFirst(plain);
                flag=true;
            }else{
                stack.addFirst(plain);
            }
        }
        int len=stack.size();
        int[] res=new int[len];
        for(int i=0;i<len;i++){
            res[i]=stack.pollLast();
        }
        return res;
    }
}
