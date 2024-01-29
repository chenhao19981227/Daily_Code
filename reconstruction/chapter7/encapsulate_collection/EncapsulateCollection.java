package reconstruction.chapter7.encapsulate_collection;

import java.util.List;

public class EncapsulateCollection {
    public void encapsulateCollection(){
        Person person=new Person("ChenHao");
        List<Course> courses = person.getCourses();
        courses.add(new Course("math",false));// 能轻易地修改信息而不被Person类监测
        person.courses=courses;
    }
}
