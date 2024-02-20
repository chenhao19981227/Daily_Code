package reconstruction.chapter12.replace_type_code_with_subclasses.after1;

public class Manager extends EmployeeAfter1 {
    public Manager(String name) {
        super(name);
    }
    public String getType(){
        return "manager";
    }
}
