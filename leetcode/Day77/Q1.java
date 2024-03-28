package leetcode.Day77;

import java.util.ArrayList;
import java.util.List;

public class Q1 {
    public List<String> getValidT9Words(String num, String[] words) {
        List<String> res = new ArrayList<>();
        int length = num.length();
        int[] key = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6,
                7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};
        s:for (String word : words) {
            for (int i = 0; i < num.length(); i++) {
                if(num.charAt(i)!=key[word.charAt(i)-'a']+'0'){
                    continue s;
                }
            }
            res.add(word);
        }
        return res;
    }
}
