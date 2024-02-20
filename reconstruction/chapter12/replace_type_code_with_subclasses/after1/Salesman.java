package reconstruction.chapter12.replace_type_code_with_subclasses.after1;

public class Salesman extends EmployeeAfter1 {
    public Salesman(String name) {
        super(name);
    }

    public String getType(){
        return "salesman";
    }
}
