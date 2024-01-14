package leetcode.Day3;

public class Q1 {
    public String mergeAlternately(String word1, String word2) {
        int len1=word1.length();
        int len2=word2.length();
        StringBuffer res=new StringBuffer();
        int index1=0,index2=0;
        while (index1<len1||index2<len2){
            if(index1<len1)res.append(word1.charAt(index1++));
            if(index2<len2)res.append(word2.charAt(index2++));
        }
        return res.toString();
    }
}
