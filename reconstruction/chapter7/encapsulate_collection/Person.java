package reconstruction.chapter7.encapsulate_collection;

import java.util.ArrayList;
import java.util.List;

public class Person {
    String name;
    List<Course> courses=new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
