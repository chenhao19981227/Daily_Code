package leetcode.Day101;

import java.util.*;

public class Q1 {
    private List<String> path = new ArrayList<>();
    private Set<String> history = new HashSet<>();

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (finish(beginWord, endWord, wordList)) {
            path.add(0, beginWord);
        }
        return path;
    }

    boolean finish(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) return true;
        if (!wordList.contains(endWord) || history.contains(beginWord)) return false;
        Iterator<String> iterator = wordList.iterator();
        int i = wordList.size() - 1;
        while (iterator.hasNext() && i >= 0) {
            String cur = wordList.get(i);
            if (near(beginWord, cur)) {
                wordList.remove(i);
                path.add(cur);
                if (finish(cur, endWord, wordList)) return true;
                history.add(cur);
                path.remove(path.size() - 1);
                wordList.add(cur);
            }
            i--;
        }
        return false;
    }

    boolean near(String str1, String str2) {
        int diff = 0;
        for (int i = 0; i < str1.length() && diff <= 1; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }

}
