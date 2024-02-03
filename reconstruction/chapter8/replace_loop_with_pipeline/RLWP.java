package reconstruction.chapter8.replace_loop_with_pipeline;

import java.util.stream.Stream;

public class RLWP {
    public static void main(String[] args) {
        String[] strings=new String[]{"doctor","nurse","worker","programmer"};
        for (String job : strings) {
            if(job.equals("programmer")){
                System.out.println(job);
            }
        }
        System.out.println("-------------------------");
        Stream.of(strings)
                .filter(job -> job.equals("programmer"))
                .forEach(System.out::println);
    }
}
