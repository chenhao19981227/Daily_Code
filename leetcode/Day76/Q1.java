package leetcode.Day76;

public class Q1 {
    public boolean patternMatching(String pattern, String value) {
        int countA=0,countB=0;
        for (char c : pattern.toCharArray()) {
            if(c=='a')
                countA++;
            else
                countB++;
        }
        if(countA<countB){
            int temp=countA;
            countA=countB;
            countB=temp;
            char[] valueArray = pattern.toCharArray();
            for (int i = 0; i < valueArray.length; i++) {
                valueArray[i]=valueArray[i]=='a'?'b':'a';
            }
            pattern=new String(valueArray);
        }
        if(value.isEmpty()) return countB==0;
        if(pattern.isEmpty()) return false;
        for(int lenA=0;lenA*countA<=value.length();lenA++){
            int rest=value.length()-lenA*countA;
            if(countB==0&&rest==0||(countB!=0&&rest%countB==0)){
                int lenB=(countB==0?0:rest/countB);
                int pos=0;
                boolean correct=true;
                String valueA="",valueB="";
                for (char ch : pattern.toCharArray()) {
                    if(ch=='a'){
                        String sub=value.substring(pos,pos+lenA);
                        if(valueA.isEmpty()){
                            valueA=sub;
                        }else{
                            if(!valueA.equals(sub)){
                                correct=false;
                                break;
                            }
                        }
                        pos+=lenA;
                    }else {
                        String sub=value.substring(pos,pos+lenB);
                        if(valueB.isEmpty()){
                            valueB=sub;
                        }else {
                            if(!valueB.equals(sub)){
                                correct=false;
                                break;
                            }
                        }
                        pos+=lenB;
                    }
                }
                if(correct&&!valueA.equals(valueB)){
                    return true;
                }
            }
        }
        return false;
    }
}
