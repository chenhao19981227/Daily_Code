package reconstruction.chapter12.replace_type_code_with_subclasses;

import reconstruction.chapter12.replace_type_code_with_subclasses.Before.Employee;
import reconstruction.chapter12.replace_type_code_with_subclasses.after1.EmployeeAfter1;
import reconstruction.chapter12.replace_type_code_with_subclasses.after2.EmployeeAfter2;

public class Test {
    public static void main(String[] args) {
        System.out.println(new Employee("ch", "engineer"));
        System.out.println(EmployeeAfter1.createEmployee("ch", "manager"));
        System.out.println(new EmployeeAfter2("ch","salesman"));
    }
}
