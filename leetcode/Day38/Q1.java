package leetcode.Day38;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q1 {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<int[]> stack=new ArrayDeque<>();
        int[] result=new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int cur=temperatures[i];
            while (!stack.isEmpty()&&stack.peek()[0]<cur){
                int[] pre = stack.pop();
                result[pre[1]]=i-pre[1];
            }
            stack.push(new int[]{cur,i});
        }
        while (!stack.isEmpty()){
            result[stack.pop()[1]]=0;
        }
        return result;
    }
}
