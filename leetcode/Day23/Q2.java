package leetcode.Day23;

import java.util.List;

public class Q2 {
    boolean[] mark;
    int count;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int len=rooms.size();
        mark=new boolean[len];
        count=0;
        dfs(rooms,0);
        return count==len;
    }
    public void dfs(List<List<Integer>> rooms,int index){
        mark[index]=true;
        count++;
        for (Integer room : rooms.get(index)) {
            if(!mark[room]){
                dfs(rooms,room);
            }
        }
    }
}
