package reconstruction.chapter12.replace_type_code_with_subclasses.Before;

import lombok.ToString;

@ToString
public class Employee {
    String name;
    String type;
    public Employee(String name, String type) {
        this.validateType(type);
        this.name = name;
        this.type = type;
    }
    private void validateType(String type){
        if(!type.equals("engineer")&&!type.equals("manager")&&!type.equals("salesman")){
            throw new Error("没有这个职业");
        }
    }
}
