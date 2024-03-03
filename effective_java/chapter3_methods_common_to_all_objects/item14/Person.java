package effective_java.chapter3_methods_common_to_all_objects.item14;

public class Person implements Comparable<Person> {
    String name;
    int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }
    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.id,o.id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
