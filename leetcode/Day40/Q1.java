package leetcode.Day40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1 {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result=new ArrayList<>();
        Arrays.sort(products);
        for (int i = 0; i < searchWord.length(); i++) {
            String prefix=searchWord.substring(0,i+1);
            List<String> list=new ArrayList<>();
            for (String product : products) {
                if(product.startsWith(prefix))list.add(product);
                if(list.size()==3)break;
            }
            result.add(list);
        }
        return result;
    }
}
