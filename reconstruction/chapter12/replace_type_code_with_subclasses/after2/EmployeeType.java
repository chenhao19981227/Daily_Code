package reconstruction.chapter12.replace_type_code_with_subclasses.after2;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class EmployeeType {
    private String type;
    public EmployeeType(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
