package reconstruction.chapter11.separate_query_from_modifier;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> people=new ArrayList<>();
        people.add("Asshole");
        people.add("Miscreant");
        people.add("ChenHao");
        Before.alertForMiscreant(people);
        System.out.println("---------------------");
        After.alertForMiscreant(people);
    }
}
