package reconstruction.chapter7.hide_delegate;

public class PersonHideDelegate {
    String name;
    Department department;
    public PersonHideDelegate(String name) {
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
