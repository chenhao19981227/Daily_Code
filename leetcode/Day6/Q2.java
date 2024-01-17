package leetcode.Day6;

public class Q2 {
    public int compress(char[] chars) {
        int n = chars.length, l = 0, r = 0, i = 0;
        while (l < n) {
            r = l + 1;
            while (r < n && chars[l] == chars[r]) r++;
            chars[i++] = chars[l];
            int cnt = r - l, t = i;
            if (cnt > 1) {
                while (cnt > 0) {
                    chars[i++] = (char)(cnt % 10 + '0');
                    cnt /= 10;
                }
                reverse(chars, t, i - 1);
            }
            l = r;
        }
        return i;
    }

    public void reverse(char[] chs, int l, int r) {
        while (l < r) {
            char t = chs[l];
            chs[l++] = chs[r];
            chs[r--] = t;
        }
    }
}
