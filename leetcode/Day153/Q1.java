package leetcode.Day153;

import java.util.ArrayList;
import java.util.List;

public class Q1 {
    public int countSymmetricIntegers(int low, int high) {
        int res=0;
        for(int i=low;i<=high;i++){
            int num=i;
            List<Integer> list=new ArrayList<>();
            while(num>=1){
                list.add(num%10);
                num/=10;
            }
            int size=list.size();
            if(size%2!=0){
                continue;
            }
            int part1=0;
            int total=0;
            for(int j=0;j<size;j++){
                if(j<size/2){
                    part1+=list.get(j);
                }
                total+=list.get(j);
            }
            if(part1*2==total){
                res++;
            }
        }
        return res;
    }
}
