package leetcode.Day24;

public class Q1 {
    boolean[] mark;
    public int findCircleNum(int[][] isConnected) {
        int res=0;
        int cityNum=isConnected.length;
        mark=new boolean[cityNum];
        for (int i = 0; i < cityNum; i++) {
            if(!mark[i]){
                res++;
                dfs(isConnected,i);
            }
        }
        return res;
    }

    private void dfs(int[][] isConnected, int city) {
        mark[city]=true;
        for (int i = 0; i < isConnected.length; i++) {
            if(isConnected[city][i]==1&&!mark[i]){
                dfs(isConnected,i);
            }
        }
    }
}
