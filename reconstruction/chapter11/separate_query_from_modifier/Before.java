package reconstruction.chapter11.separate_query_from_modifier;

import java.util.List;

public class Before {
    public static String alertForMiscreant(List<String> people){
        for (String person : people) {
            if(person.equals("Miscreant")){
                setOfAlert();
                return "Miscreant";
            }
            if(person.equals("Asshole")){
                setOfAlert();
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
