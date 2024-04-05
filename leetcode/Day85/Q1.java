package leetcode.Day85;

public class Q1 {
    public int findClosest(String[] words, String word1, String word2) {
        int index1=-1,index2=-1;
        int res=Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String word=words[i];
            if(word.equals(word1)){
                index1=i;
                if(index2!=-1)
                    res=Math.min(i-index2,res);
            }
            if(word.equals(word2)){
                index2=i;
                if(index1!=-1)
                    res=Math.min(i-index1,res);
            }
        }
        return res;
    }
}
