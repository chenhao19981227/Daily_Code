package leetcode.Day125;

import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String rule = scanner.nextLine();
        String str = scanner.nextLine();

        String ruleSimple = expandString(rule);
        StringBuilder strSimple = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                strSimple.append("N");
            }
            if (Character.isLetter(ch)) {
                strSimple.append("A");
            }
        }

        int pos = strSimple.indexOf(ruleSimple);
        if (pos == -1) {
            System.out.println("!");
            return;
        }

        int size = ruleSimple.length();
        String res = str.substring(pos, pos + size);
        System.out.println(res);
    }
    public static String expandString(String input) {
        StringBuilder result = new StringBuilder();
        StringBuilder current = new StringBuilder();
        int repeat = 0;
        int i = 0;

        while (i < input.length()) {
            if (Character.isDigit(input.charAt(i))) {
                repeat = repeat * 10 + (input.charAt(i) - '0');
            } else if (input.charAt(i) == '(') {
                int j = i + 1;
                int count = 1;
                current.setLength(0);

                while (j < input.length()) {
                    if (input.charAt(j) == '(') {
                        count++;
                    } else if (input.charAt(j) == ')') {
                        count--;
                        if (count == 0) {
                            break;
                        }
                    }

                    current.append(input.charAt(j));
                    j++;
                }

                String expanded = expandString(current.toString());

                for (int k = 0; k < repeat; k++) {
                    result.append(expanded);
                }

                repeat = 0;
                i = j;
            } else {
                result.append(input.charAt(i));
            }

            i++;
        }

        return result.toString();
    }
}
