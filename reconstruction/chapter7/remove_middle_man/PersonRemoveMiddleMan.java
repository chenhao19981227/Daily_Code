package reconstruction.chapter7.remove_middle_man;

public class PersonRemoveMiddleMan {
    String name;
    Department department;

    public PersonRemoveMiddleMan(String name) {
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
}
