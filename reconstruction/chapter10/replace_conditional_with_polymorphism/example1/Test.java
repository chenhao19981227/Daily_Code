package reconstruction.chapter10.replace_conditional_with_polymorphism.example1;

import reconstruction.chapter10.replace_conditional_with_polymorphism.example1.after.*;
import reconstruction.chapter10.replace_conditional_with_polymorphism.example1.before.BirdsInfo;

public class Test {
    public static void main(String[] args) {
        BirdsInfo.Bird bird=new BirdsInfo.Bird("AfricanSwallow",3,100,false);
        System.out.println(BirdsInfo.plumage(bird));
        System.out.println(BirdsInfo.airSpeedVelocity(bird));
        System.out.println("----------------------------");
        Bird newBird=new Bird("AfricanSwallow",3,100,false);
        System.out.println(BirdsInfoAfterReplace.plumage(newBird));
        System.out.println(BirdsInfoAfterReplace.airSpeedVelocity(newBird));
    }
}
