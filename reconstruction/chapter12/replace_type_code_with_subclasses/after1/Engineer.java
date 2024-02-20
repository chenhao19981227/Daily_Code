package reconstruction.chapter12.replace_type_code_with_subclasses.after1;

public class Engineer extends EmployeeAfter1 {
    public Engineer(String name) {
        super(name);
    }

    public String getType(){
        return "engineer";
    }
}
