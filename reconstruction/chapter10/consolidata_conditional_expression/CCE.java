package reconstruction.chapter10.consolidata_conditional_expression;

public class CCE {
    static int a=2,b=5,c=8;
    public static void main(String[] args) {
        if(a>3)return;
        if(b<4)return;
        if(c>10)return;
        System.out.println("重构后");
        if(judge())return;
    }
    public static boolean judge(){
        return a>3||b<4||c>10;
    }
}
