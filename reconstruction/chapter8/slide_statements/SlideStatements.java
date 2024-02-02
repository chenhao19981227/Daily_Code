package reconstruction.chapter8.slide_statements;

public class SlideStatements {
    public int beforeSlideStatements(int a,int b){
        int c=0;
        if(a>0){
            c=a+b;
            c*=b;
        }else {
            c=a-b;
            c*=b;
        }
        return c;
    }
    public int afterSlideStatements(int a,int b){
        int c=0;
        if(a>0){
            c=a+b;
        }else {
            c=a-b;
        }
        c*=b;
        return c;
    }

    public static void main(String[] args) {
        SlideStatements slideStatements=new SlideStatements();
        System.out.println(slideStatements.beforeSlideStatements(1, 2));
        System.out.println("-------------------------------------------------");
        System.out.println(slideStatements.afterSlideStatements(1,2));
    }
}
