package effective_java.chapter3_methods_common_to_all_objects.item14;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Person p1=new Person("a",1);
        Person p2=new Person("b",4);
        Person p3=new Person("c",0);
        Person[] persons=new Person[]{p3,p1,p2};
        Arrays.sort(persons);
        for (Person person : persons) {
            System.out.println(person);
        }

        Person2 p4=new Person2("a",1);
        Person2 p5=new Person2("b",2);
        Person2 p6=new Person2("c",3);
        Person2[] persons2=new Person2[]{p4,p6,p5};
        Arrays.sort(persons);
        for (Person2 person2 : persons2) {
            System.out.println(person2);
        }

        Arrays.sort(persons, (o1, o2) -> o1.name.compareTo(o2.name));
        for (Person person : persons) {
            System.out.println(person);
        }
    }
}
