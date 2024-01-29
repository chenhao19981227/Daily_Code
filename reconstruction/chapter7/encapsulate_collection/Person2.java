package reconstruction.chapter7.encapsulate_collection;

import java.util.ArrayList;
import java.util.List;

public class Person2 {
    String name;
    private List<Course> courses=new ArrayList<>();

    public Person2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }
    public void addCourse(Course course){
        this.courses.add(course);
    }
    public void removeCourse(Course course){
        int index = this.courses.indexOf(course);
        if (index == -1) {
            throw new ArrayIndexOutOfBoundsException();
        }else {
            this.courses.remove(course);
        }
    }
}
