package leetcode.Day86;

import java.util.Arrays;

public class Q1 {
    public int respace(String[] dictionary, String sentence) {
        int len=sentence.length();
        int[] dp=new int[len+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        Trie trie=new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }
        dp[0]=0;
        for(int i=1;i<=len;i++){
            dp[i]=dp[i-1]+1;
            Trie cur=trie;
            for(int j=i;j>=1;j--){
                int index=sentence.charAt(j-1)-'a';
                if(cur.next[index]==null)
                    break;
                else if(cur.next[index].isEnd)
                    dp[i]=Math.min(dp[i],dp[j-1]);
                cur=cur.next[index];

            }
        }
        return dp[len];
    }
}
class Trie {
    public Trie[] next;
    public boolean isEnd;
    public Trie(){
        next=new Trie[26];
        isEnd=false;
    }
    public void insert(String s){
        Trie cur=this;
        for (int i = s.length()-1; i >=0; i--) {
            int index=s.charAt(i)-'a';
            if(cur.next[index]==null){
                cur.next[index]=new Trie();
            }
            cur=cur.next[index];
        }
        cur.isEnd=true;
    }
}
