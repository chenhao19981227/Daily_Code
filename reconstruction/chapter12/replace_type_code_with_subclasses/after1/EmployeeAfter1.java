package reconstruction.chapter12.replace_type_code_with_subclasses.after1;

import lombok.ToString;

@ToString
public class EmployeeAfter1 {
    String name;
    public EmployeeAfter1(String name) {
        this.name = name;
    }
    public static EmployeeAfter1 createEmployee(String name, String type){
        switch (type){
            case "engineer":return new Engineer(name);
            case "manager":return new Manager(name);
            case "salesman":return new Salesman(name);
            default:throw new Error("没有这个职业");
        }
    }
}
