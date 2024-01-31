package reconstruction.chapter7.remove_middle_man;


public class Test {
    public static void main(String[] args) {
        Department department=new Department("001","boss");
        Person person=new Person("ChenHao");
        person.setDepartment(department);
        System.out.println(person.getManager());
        System.out.println("---------------------------------");
        PersonRemoveMiddleMan personHideDelegate=new PersonRemoveMiddleMan("ChenHao");
        personHideDelegate.setDepartment(department);
        System.out.println(personHideDelegate.getDepartment().getManger());
    }
}
