package leetcode.Day74;

public class Q2 {
    public int[] masterMind(String solution, String guess) {
        int right=0,total=0;
        int[] mark1=new int[26];
        int[] mark2=new int[26];
        for (int i = 0; i < 4; i++) {
            if(solution.charAt(i)==guess.charAt(i))
                right++;
            mark1[solution.charAt(i)-'A']++;
            mark2[guess.charAt(i)-'A']++;
        }
        for (int i = 0; i < 26; i++) {
            total+=Math.min(mark1[i],mark2[i]);
        }
        return new int[]{right,total-right};
    }
}
