package leetcode.Day73;

public class Q1 {
    String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] thousands = {"", "Thousand", "Million", "Billion"};
    public String numberToWords(int num) {
        if(num==0)return "Zero";
        StringBuilder res=new StringBuilder();
        for(int i=3,unit=1000000000;i>=0;i--,unit/=1000){
            int cur=num/unit;
            if(cur!=0){
                num-=cur*unit;
                StringBuilder sb=new StringBuilder();
                recursion(sb,cur);
                sb.append(thousands[i]).append(" ");
                res.append(sb);
            }
        }
        return res.toString().trim();
    }

    private void recursion(StringBuilder sb, int cur) {
        if(cur==0)
            return;
        else if(cur<10)
            sb.append(singles[cur]).append(" ");
        else if(cur<20)
            sb.append(teens[cur-10]).append(" ");
        else if(cur<100){
            sb.append(tens[cur/10]).append(" ");
            recursion(sb,cur%10);
        }else{
            sb.append(singles[cur/100]).append(" Hundred ");
            recursion(sb,cur%100);
        }
    }
}
