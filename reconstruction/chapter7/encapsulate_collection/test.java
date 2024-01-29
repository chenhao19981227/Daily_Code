package reconstruction.chapter7.encapsulate_collection;

import java.util.List;

public class test {
    public static void main(String[] args) {
        Person2 person=new Person2("ChenHao");
        List<Course> courses = person.getCourses();
        Course course = new Course("math", false);
        person.addCourse(course);
        List<Course> courses1 = person.getCourses();
        for (Course course1 : courses1) {
            System.out.println(course1.name);
        }
    }
}
