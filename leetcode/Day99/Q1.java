package leetcode.Day99;

public class Q1 {
    // 给定一个01字符串,每次操作可以将某一个字符'1'变成'0'
    // 现在希望经过最多k次操作后合法子串长度的最大值尽可能小
    // 请你计算这个最小的最大的值。
    // 定义一段全部为字符'1'组成的字符串为合法子串,例如对于字符串"101110110"而言,最长合法子串的长度为3。
    public int maxLen(String s, int k) {
        int n=s.length();
        int l = 0, r = n;
        while (l <= r) {
            int m = (l + r) / 2;
            int cnt = 0;
            for (int i = 0; i < n; ) {
                if (s.charAt(i) == '0') {++i; continue;}
                int j = i;
                boolean ok = true;
                while (j < n &&s.charAt(j) == '1') {
                    if (j - i + 1 > m) { ok = false; break; }
                    ++j;
                }
                if (!ok) {
                    ++cnt;
                }
                i = j + 1;
            }
            if (cnt <= k) r = m - 1;
            else l = m + 1;
        }
        return l;
    }
}
