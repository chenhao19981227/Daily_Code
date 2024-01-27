package leetcode.Day16;

import java.util.LinkedList;
import java.util.Queue;

public class Q1 {
    public String predictPartyVictory(String senate) {
        int len=senate.length();
        Queue<Integer> radiant =new LinkedList<>();
        Queue<Integer> dire=new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if(senate.charAt(i)=='R'){
                radiant.offer(i);
            }else {
                dire.offer(i);
            }
        }
        while (!radiant.isEmpty()&&!dire.isEmpty()){
            int radiantFirst=radiant.poll();
            int direFirst=dire.poll();
            if(radiantFirst<direFirst){
                radiant.offer(radiantFirst+len);
            }else {
                dire.offer(direFirst+len);
            }
        }
        return radiant.isEmpty()?"Dire":"Radiant";
    }
}
