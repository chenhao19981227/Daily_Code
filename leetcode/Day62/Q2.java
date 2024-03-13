package leetcode.Day62;

import java.util.HashSet;
import java.util.Set;

public class Q2 {
    static Set<String> set;
    public String[] permutation(String S) {
        set=new HashSet<>();
        boolean[] mark=new boolean[S.length()];
        dfs(S,"",mark,0);
        return set.toArray(new String[set.size()]);
    }
    private static void dfs(String str,String cur,boolean[] mark,int index){
        if(index==str.length()){
            set.add(cur);
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
