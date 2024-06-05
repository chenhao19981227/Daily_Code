package leetcode.Day146;

import java.util.Arrays;

public class Q1 {
    public String customSortString(String order, String s) {
        int[] val = new int[26];
        for (int i = 0; i < order.length(); ++i) {
            val[order.charAt(i) - 'a'] = i + 1;
        }
        Character[] arr = new Character[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            arr[i] = s.charAt(i);
        }
        Arrays.sort(arr, (c0, c1) -> val[c0 - 'a'] - val[c1 - 'a']);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            ans.append(arr[i]);
        }
        return ans.toString();
    }
}
