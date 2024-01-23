package leetcode.Day12;

import java.util.Arrays;

public class Q2 {
    public boolean closeStrings(String word1, String word2) {
        if(word1.length()!=word2.length())return false;
        int[] index1=new int[26];
        int[] index2=new int[26];
        for (int i = 0; i < word1.length(); i++) {
            index1[word1.charAt(i)-'a']++;
        }
        for (int i = 0; i < word2.length(); i++) {
            index2[word2.charAt(i)-'a']++;
        }
        for (int i = 0; i < index1.length; i++) {
            if(index1[i]==0&&index2[i]!=0||index1[i]!=0&&index2[i]==0)return false;
        }
        Arrays.sort(index1);
        Arrays.sort(index2);
        for (int i = 0; i < index1.length; i++) {
            if(index1[i]!=index2[i])return false;
        }
        return true;
    }
}
