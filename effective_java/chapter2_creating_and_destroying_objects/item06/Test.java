package effective_java.chapter2_creating_and_destroying_objects.item06;

public class Test {
    public static void main(String[] args) {
        RomanN test=new RomanN();
        long start = System.currentTimeMillis();
        System.out.println(test.isRomanNumeral("test"));
        System.out.println(test.isRomanNumeral("test2"));
        System.out.println(test.isRomanNumeral("test3"));
        System.out.println(test.isRomanNumeral("VI"));
        System.out.println(System.currentTimeMillis()-start);

        /**
         * 下面这种方式确实耗时更短
         */
        long start2 = System.currentTimeMillis();
        System.out.println(test.isRomanNumeral2("test"));
        System.out.println(test.isRomanNumeral2("test2"));
        System.out.println(test.isRomanNumeral2("test3"));
        System.out.println(test.isRomanNumeral2("VI"));
        System.out.println(System.currentTimeMillis()-start2);

        long start3=System.currentTimeMillis();
        sum1();
        System.out.println(System.currentTimeMillis()-start3);
        long start4=System.currentTimeMillis();
        sum2();
        System.out.println(System.currentTimeMillis()-start4);
    }
    private static void sum1(){
        Long sum=0L;
        for(long i=0;i<=Integer.MAX_VALUE;i++){
            sum+=i;
        }
    }
    private static void sum2(){
        long sum=0L;
        for(long i=0;i<=Integer.MAX_VALUE;i++){
            sum+=i;
        }
    }
}
