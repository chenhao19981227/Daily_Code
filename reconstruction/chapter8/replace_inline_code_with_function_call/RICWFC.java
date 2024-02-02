package reconstruction.chapter8.replace_inline_code_with_function_call;

import java.util.Arrays;

public class RICWFC {
    public static void main(String[] args) {
        String[] states=new String[10];
        states[0]="MA";
        boolean appliesToMass = false;
        for(String s:states) {
            if(s.equals("MA")) {
                appliesToMass = true;
                break; // Exit the loop once we find "MA"
            }
        }
        System.out.println(appliesToMass);
        appliesToMass=false;
        appliesToMass = Arrays.stream(states).anyMatch(s -> s.equals("MA"));
        System.out.println(appliesToMass);
    }
}
