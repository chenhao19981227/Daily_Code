package leetcode.Day106;

import java.util.ArrayList;
import java.util.List;

public class Q1 {
    public int[][] multiSearch(String big, String[] smalls) {
        int n = smalls.length;
        int[][] res = new int[n][];
        for (int i = 0; i < n; i++) {
            res[i] = lst2Arr(search(big, smalls[i]));
        }
        return res;
    }

    List<Integer> search (String big, String s) {
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0) return res;
        int idx = big.indexOf(s);
        while (idx != -1) {
            res.add(idx);
            idx = big.indexOf(s, idx + 1);
        }
        return res;
    }

    int[] lst2Arr(List<Integer> lst) {
        int[] res = new int[lst.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = lst.get(i);
        }
        return res;
    }
}
