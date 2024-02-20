package reconstruction.chapter12.replace_type_code_with_subclasses.after2;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class EmployeeAfter2 {
    String name;
    EmployeeType type;
    public EmployeeAfter2(String name, String employeeType) {
        this.name = name;
        this.type=createEmployeeType(employeeType);
    }

    public void setType(String type) {
        this.type=createEmployeeType(type);
    }

    public static EmployeeType createEmployeeType(String type){
        switch (type){
            case "engineer":return new Engineer();
            case "manager":return new Manager();
            case "salesman":return new Salesman();
            default:throw new Error("没有这个职业");
        }
    }
}
