package leetcode.Day41;

public class Q1 {
    public boolean isUnique(String astr) {
        int[] mark=new int[26];
        for (char c : astr.toCharArray()) {
            int index=c-'a';
            if(mark[index]!=0)return false;
            mark[index]++;
        }
        return true;
    }
}
