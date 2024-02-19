package leetcode.Day39;

public class Trie2 {
    Trie2[] children;
    boolean isEnd;
    public Trie2() {
        children=new Trie2[26];
        isEnd=false;
    }

    public void insert(String word) {
        Trie2 node=this;
        for (char c : word.toCharArray()) {
            int index=c-'a';
            if(node.children[index]==null) node.children[index]=new Trie2();
            node=node.children[index];
        }
        node.isEnd=true;
    }

    public boolean search(String word) {
        Trie2 node=searchPrefix(word);
        return node!=null&&node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix)!=null;
    }
    public Trie2 searchPrefix(String word){
        Trie2 node=this;
        for (char c : word.toCharArray()) {
            int index=c-'a';
            if(node.children[index]==null)return null;
            node=node.children[index];
        }
        return node;
    }
}
