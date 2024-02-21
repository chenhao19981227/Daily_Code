package leetcode.Day41;

public class Q2 {
    public boolean CheckPermutation(String s1, String s2) {
        if(s1.length()!=s2.length())return false;
        int[] mark=new int[26];
        for (char c : s1.toCharArray()) {
            mark[c-'a']++;
        }
        for (char c : s2.toCharArray()) {
            if(--mark[c-'a']<0)return false;
        }
        return true;
    }
}
