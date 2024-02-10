package leetcode.Day30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q1 {
    Map<Character,String> map;
    public List<String> letterCombinations(String digits) {
        List<String> combinations=new ArrayList<>();
        if(digits.isEmpty())return combinations;
        map=new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        backtrack(combinations,digits,0,new StringBuilder());
        return combinations;
    }

    private void backtrack(List<String> combinations, String digits, int index, StringBuilder stringBuilder) {
        if(index==digits.length()){
            combinations.add(stringBuilder.toString());
            return;
        }
        char num = digits.charAt(index);
        String letters = map.get(num);
        for (int i = 0; i < letters.length(); i++) {
            stringBuilder.append(letters.charAt(i));
            backtrack(combinations,digits,index+1,stringBuilder);
            stringBuilder.deleteCharAt(index);
        }
    }
}
