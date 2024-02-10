package leetcode.Day30;

import java.util.ArrayList;
import java.util.List;

public class Q2 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result=new ArrayList<>();
        backtrack(result,0,k,n,new ArrayList<Integer>());
        return result;
    }

    private void backtrack(List<List<Integer>> result, int pre,int k,int need, ArrayList<Integer> combination) {
        if(combination.size()==k){
            if(need==0){
                result.add(new ArrayList<>(combination));
            }
            return;
        }
        for(int i=pre+1;i<=9;i++){
            need-=i;
            if(need<0)break;
            combination.add(i);
            backtrack(result,i,k,need,combination);
            need+=i;
            combination.remove(combination.size()-1);
        }
    }
}
