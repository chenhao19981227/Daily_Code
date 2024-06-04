package leetcode.Day145;

public class Q1 {
    public boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];
        for (int i = 0; i < order.length(); ++i) {
            map[order.charAt(i) - 'a'] = i; // 更遍历到的字母的序号更大。
        }
        int i = 0; int j = 1; // 两两比较大小，按照map中的数值
        while (j < words.length) {
            char[] ch1 = words[i].toCharArray();
            char[] ch2 = words[j].toCharArray();
            ++i;++j;
            int a, b;
            for (a = 0, b = 0; a < ch1.length && b < ch2.length; ++a, ++b) {
                if (map[ch1[a] - 'a'] > map[ch2[b] - 'a']) return false; // 如果前面更大;
                if (map[ch1[a] - 'a'] < map[ch2[b] - 'a']) break; // 如果前面更小
            }
            // 如果前面更小，就比较下一对
            if (a < ch1.length && b < ch2.length && map[ch1[a] - 'a'] < map[ch2[b] - 'a']) continue;
            if (ch1.length > ch2.length) return false; // 如果前面的字符都相等，字符串更长的更大；
        }
        return true;
    }

}
