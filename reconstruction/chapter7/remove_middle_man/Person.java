package reconstruction.chapter7.remove_middle_man;


public class Person {
    String name;
    Department department;
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    public String getManager(){
        return this.department.getManger();
    }
}
