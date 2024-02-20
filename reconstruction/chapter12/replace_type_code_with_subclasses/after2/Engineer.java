package reconstruction.chapter12.replace_type_code_with_subclasses.after2;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Engineer extends EmployeeType{
    public Engineer(String type) {
        super(type);
    }

    @Override
    public String getType() {
        return "engineer";
    }
}
