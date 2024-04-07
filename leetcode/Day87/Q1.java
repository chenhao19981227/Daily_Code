package leetcode.Day87;

import java.util.Arrays;

public class Q1 {
    Trie root=new Trie();
    public String longestWord(String[] words) {
        Arrays.sort(words,(a,b) -> (a.length() == b.length()? a.compareTo(b) : b.length() - a.length()));
        for (String word : words) {
            root.insert(word);
        }
        for (String word : words) {
            if(judge(word,word)){
                return word;
            }
        }
        return "";
    }

    private boolean judge(String word,String sample) {
        Trie cur=root;
        if(word.isEmpty()) return true;
        for (int i = 0; i < word.length(); i++) {
            int index=word.charAt(i)-'a';
            if(cur.next[index]==null) return false;
            if(cur.next[index].isEnd&&!word.substring(0,i+1).equals(sample)&&judge(word.substring(i+1),sample))
                return true;
            cur=cur.next[index];
        }
        return false;
    }
}
class Trie{
    Trie[] next;
    boolean isEnd;
    public Trie(){
        next=new Trie[26];
        isEnd=false;
    }
    public void insert(String s){
        Trie cur=this;
        for (int i = 0; i < s.length(); i++) {
            int index=s.charAt(i)-'a';
            if(cur.next[index]==null){
                cur.next[index]=new Trie();
            }
            cur=cur.next[index];
        }
        cur.isEnd=true;
    }
}
