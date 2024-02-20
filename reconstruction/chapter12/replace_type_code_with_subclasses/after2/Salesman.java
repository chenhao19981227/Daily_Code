package reconstruction.chapter12.replace_type_code_with_subclasses.after2;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Salesman extends EmployeeType{
    public Salesman(String type) {
        super(type);
    }

    @Override
    public String getType() {
        return "salesman";
    }
}
