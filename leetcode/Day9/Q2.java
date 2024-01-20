package leetcode.Day9;

public class Q2 {
    public int maxVowels(String s, int k) {
        int len=s.length();
        int count=0;
        for (int i = 0; i < k; i++) {
            count+=isVowel(s.charAt(i));
        }
        int max=count;
        for(int i=k;i<len;i++){
            count+=isVowel(s.charAt(i));
            count-=isVowel(s.charAt(i-k));
            max=Math.max(max,count);
        }
        return max;
    }
    private int isVowel(char c) {
        if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){
            return 1;
        }
        return 0;
    }
}
