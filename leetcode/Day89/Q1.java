package leetcode.Day89;

import java.util.HashMap;
import java.util.Map;

public class Q1 {
    public int[] shortestSeq(int[] big, int[] small) {
        int left=0,right=0;
        int l=-1,r=big.length;
        Map<Integer,Integer> map=new HashMap<>();
        Map<Integer,Integer> mark=new HashMap<>();
        for (int num : small) {
            map.put(num,1);
        }
        while (right<big.length){
            if(mark.size()<small.length){
                if(map.containsKey(big[right]))
                    mark.put(big[right],mark.getOrDefault(big[right],0)+1);
                right++;
            }
            if(mark.size()==small.length){
                while ((!mark.containsKey(big[left]))||mark.get(big[left])>1){
                    if(mark.containsKey(big[left])){
                        mark.put(big[left],mark.get(big[left])-1);
                    }
                    left++;
                }
                if(right-left<r-l){
                    l=left;
                    r=right;
                }
                mark.remove(big[left++]);
            }
        }
        return l==-1?new int[]{}:new int[]{l,r-1};
    }

}
