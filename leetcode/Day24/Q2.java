package leetcode.Day24;

import java.util.LinkedList;
import java.util.Queue;

public class Q2 {
    public int findCircleNum(int[][] isConnected) {
        int cityNum=isConnected.length;
        Queue<Integer> queue=new LinkedList<>();
        boolean[] mark=new boolean[cityNum];
        int res=0;
        for (int i = 0; i < cityNum; i++) {
            if(!mark[i]){
                queue.offer(i);
                while (!queue.isEmpty()){
                    int cur=queue.poll();
                    for (int j = 0; j < cityNum; j++) {
                        if(isConnected[cur][j]==1&&!mark[j]){
                            queue.offer(j);
                            mark[j]=true;
                        }
                    }
                }
                res++;
            }
        }
        return res;
    }
}
