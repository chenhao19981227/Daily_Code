package reconstruction.chapter6.inline_method;

import java.util.Map;

public class test {
    public static void main(String[] args) {
        InlineMethod inlineMethod=new InlineMethod();
        Customer customer=new Customer("ChenHao","Guangdong");
        Map<String, String> result = inlineMethod.reportLines(customer);
        for (String s : result.keySet()) {
            System.out.println(s+":"+result.get(s));
        }
        System.out.println("-------------------------------");
        AfterInline afterInline=new AfterInline();
        Map<String, String> result2 = afterInline.reportLines(customer);
        for (String s : result2.keySet()) {
            System.out.println(s+":"+result2.get(s));
        }
    }
}
