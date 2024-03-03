package effective_java.chapter3_methods_common_to_all_objects.item14;

import java.util.Comparator;

public class Person2 implements Comparator<Person2> {
    String name;
    int id;
    public Person2(String name, int id) {
        this.name = name;
        this.id = id;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int compare(Person2 o1, Person2 o2) {
        return o1.name.compareTo(o2.name);
    }
}
