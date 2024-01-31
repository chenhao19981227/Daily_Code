package reconstruction.chapter7.hide_delegate;


public class Test {
    public static void main(String[] args) {
        Department department=new Department("001","boss");
        Person person=new Person("ChenHao");
        person.setDepartment(department);
        System.out.println(person.getDepartment().getManger());
        System.out.println("---------------------------------");
        PersonHideDelegate personHideDelegate=new PersonHideDelegate("ChenHao");
        personHideDelegate.setDepartment(department);
        System.out.println(personHideDelegate.getManager());
    }
}
