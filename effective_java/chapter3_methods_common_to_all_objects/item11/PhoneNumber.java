package effective_java.chapter3_methods_common_to_all_objects.item11;

import java.util.Objects;

public class PhoneNumber {
    private final int areaCode,prefix,lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode,999,"area code");
        this.prefix = rangeCheck(prefix,999,"prefix");
        this.lineNum = rangeCheck(lineNum,9999,"line num");
    }
    private static int rangeCheck(int val,int max,String arg){
        if(val<0||val>max){
            throw new IllegalArgumentException(arg+":"+val);
        }
        return val;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj==this)return true;
        if(!(obj instanceof PhoneNumber)) return false;
        PhoneNumber phoneNumber = (PhoneNumber) obj;
        return phoneNumber.areaCode==this.areaCode&&phoneNumber.prefix==this.prefix
                &&phoneNumber.lineNum==this.lineNum;
    }
    @Override
    public int hashCode(){
        int result=Integer.hashCode(areaCode);
        result=31*result+Integer.hashCode(prefix);
        result=31*result+Integer.hashCode(lineNum);
        return result;
    }

    public int hashCode2(){
        return Objects.hash(areaCode,prefix,lineNum);
    }
    private int hashCode;
    public int hashCode3(){
        int result=hashCode;
        if(result==0){
            result=Integer.hashCode(areaCode);
            result=31*result+Integer.hashCode(prefix);
            result=31*result+Integer.hashCode(lineNum);
        }
        return result;
    }
}
