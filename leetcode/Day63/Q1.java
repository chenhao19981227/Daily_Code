package leetcode.Day63;

import java.util.ArrayList;
import java.util.List;

public class Q1 {
    static List<String> result;
    static int num;
    public List<String> generateParenthesis(int n) {
        result=new ArrayList<>();
        num=n;
        dfs(new StringBuilder(),0,0);
        return result;
    }
    private static void dfs(StringBuilder sb,int left,int right){
        if(left==num&&right==num){
            result.add(sb.toString());
            return;
        }
        if(left<num){
            sb.append("(");
            dfs(sb,left+1,right);
            sb.deleteCharAt(sb.length()-1);
        }
        if(right<left){
            sb.append(")");
            dfs(sb,left,right+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
