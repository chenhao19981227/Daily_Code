package functional_programming.method_reference;

public class Example3 {
    public interface UseString{
        String use(String str,int start,int length);
    }
    public static String subAuthorName(String str, UseString useString){
        int start = 0;
        int length = 3;
        return useString.use(str,start,length);
    }
    public static void main(String[] args) {
        System.out.println(subAuthorName("ChenHao", new UseString() {
            @Override
            public String use(String str, int start, int length) {
                return str.substring(start, length);
            }
        }));
        System.out.println(subAuthorName("ChenHao", String::substring));
    }
}
