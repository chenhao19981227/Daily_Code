package reconstruction.chapter10.replace_nested_conditional_with_guard_clauses;

public class RNCWGC {
    static boolean isDead;
    static boolean isSeparated;
    static boolean isRetired;
    public static void main(String[] args) {
        isDead=false;
        isSeparated=false;
        isRetired=false;
        System.out.println(getPayment());
        System.out.println(getPaymentRNCWGC());
    }
    public static int getPayment(){
        int result=0;
        if(isDead){
            result=deadAmount();
        }else {
            if(isSeparated){
                result=separatedAmount();
            }else {
                if(isRetired){
                    result=retiredAmount();
                }else {
                    result=normalPayAmount();
                }
            }
        }
        return result;
    }
    public static int getPaymentRNCWGC(){
        if(isDead)return deadAmount();
        if(isSeparated)return separatedAmount();
        if(isRetired)return retiredAmount();
        return normalPayAmount();
    }

    private static int normalPayAmount() {
        return 10;
    }

    private static int retiredAmount() {
        return 2;
    }

    private static int separatedAmount() {
        return 1;
    }

    private static int deadAmount() {
        return 0;
    }
}
