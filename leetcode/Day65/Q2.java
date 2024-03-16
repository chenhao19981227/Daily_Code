package leetcode.Day65;

import java.util.HashMap;
import java.util.Map;

public class Q2 {
    private Map<String, Integer> memo = new HashMap<>();

    public int countEval(String s, int result) {
        // 如果字符串为空，无法形成有效的布尔表达式
        if (s.isEmpty()) return 0;
        // 如果字符串长度为1，直接判断与期望结果是否一致
        if (s.length() == 1) return (s.charAt(0) - '0') == result ? 1 : 0;
        // 使用记忆化避免重复计算
        String key = s + result;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ways = 0;
        for (int i = 1; i < s.length(); i += 2) {
            char op = s.charAt(i);
            if(op!='&'&&op!='|'&&op!='^') return 0;
            String left = s.substring(0, i);
            String right = s.substring(i + 1);

            // 计算左右两边为真或假的情况数量
            int leftTrue = countEval(left, 1);
            int leftFalse = countEval(left, 0);
            int rightTrue = countEval(right, 1);
            int rightFalse = countEval(right, 0);

            int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);
            int totalTrue = 0;

            switch (op) {
                case '&':
                    totalTrue = leftTrue * rightTrue;
                    break;
                case '|':
                    totalTrue = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
                    break;
                case '^':
                    totalTrue = leftFalse * rightTrue + leftTrue * rightFalse;
                    break;
            }

            int subWays = result == 1 ? totalTrue : total - totalTrue;
            ways += subWays;
        }
        memo.put(key, ways);
        return ways;
    }
}
