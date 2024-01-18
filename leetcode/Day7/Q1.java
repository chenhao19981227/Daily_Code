package leetcode.Day7;

import java.util.HashMap;
import java.util.Map;

public class Q1 {
    public String reverseVowels(String s) {
        int len=s.length();
        int left=0,right=len-1;
        Map<Character,Integer> direct=new HashMap<>();
        direct.put('a',1);
        direct.put('e',1);
        direct.put('i',1);
        direct.put('o',1);
        direct.put('u',1);
        direct.put('A',1);
        direct.put('E',1);
        direct.put('I',1);
        direct.put('O',1);
        direct.put('U',1);
        char[] chars = s.toCharArray();
        while (left<right){
            while (left<right&&!direct.containsKey(chars[left]))left++;
            while (left<right&&!direct.containsKey(chars[right]))right--;
            if (left<right){
                char c=chars[left];
                chars[left]=chars[right];
                chars[right]=c;
                left++;
                right--;
            }
        }
        return String.valueOf(chars);
    }

}
