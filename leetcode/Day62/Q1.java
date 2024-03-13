package leetcode.Day62;

import java.util.ArrayList;
import java.util.List;

public class Q1 {
    static List<String> list;
    public String[] permutation(String S) {
        list=new ArrayList<>();
        boolean[] mark=new boolean[S.length()];
        dfs(S,"",mark,0);
        String[] result=new String[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i]=list.get(i);
        }
        return result;
    }
    private static void dfs(String str,String cur,boolean[] mark,int index){
        if(index==str.length()){
            list.add(cur);
        }
        for (int i = 0; i < str.length(); i++) {
            if(!mark[i]){
                mark[i]=true;
                dfs(str,cur+str.charAt(i),mark,index+1);
                mark[i]=false;
            }
        }
    }

}
