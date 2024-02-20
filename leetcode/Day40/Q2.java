package leetcode.Day40;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q2 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String,Integer> map=new HashMap<>();
        int count=1;
        for (List<String> equation : equations) {
            for (String s : equation) {
                if(!map.containsKey(s)) map.put(s,count++);
            }
        }
        double[][] graph=new double[count][count];
        for (int i = 0; i < count; i++) {
            graph[i][i]=1.0;
        }
        for (int i = 0; i < equations.size(); i++) {
            int dividend_idx=map.get(equations.get(i).get(0));
            int divisor_idx=map.get(equations.get(i).get(1));
            graph[dividend_idx][divisor_idx]=values[i];
            graph[divisor_idx][dividend_idx]=1/values[i];
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                for (int k = 0; k < count; k++) {
                    if(j==k||graph[j][k]!=0)continue;
                    graph[j][k]=graph[j][i]*graph[i][k];
                }
            }
        }
        double[] result=new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String dividend=query.get(0);
            String divisor=query.get(1);
            if(map.containsKey(dividend)&&map.containsKey(divisor)){
                int dividend_idx=map.get(dividend);
                int divisor_idx=map.get(divisor);
                double remainder = graph[dividend_idx][divisor_idx];
                result[i]= remainder ==0?-1.0:remainder;
            }else {
                result[i]=-1.0;
            }
        }
        return result;
    }
}
