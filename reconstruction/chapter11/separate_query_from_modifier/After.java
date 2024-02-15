package reconstruction.chapter11.separate_query_from_modifier;

import java.util.List;

public class After {
    public static void alertForMiscreant(List<String> people){
        if(!findMiscreant(people).isEmpty())setOfAlert();
    }
    public static String findMiscreant(List<String> people){
        for (String person : people) {
            if(person.equals("Miscreant")){
                return "Miscreant";
            }
            if(person.equals("Asshole")){
                return "Asshole";
            }
        }
        return "";
    }
    private static void setOfAlert() {
        // 将警报进行记录
        // ............
        System.out.println("Help");
    }
}
