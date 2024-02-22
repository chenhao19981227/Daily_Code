package reconstruction.chapter12.extract_superclass.before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Employee a=new Employee("a",1,1000);
        Employee b=new Employee("b",1,3000);
        Employee c=new Employee("c",1,2000);
        List<Employee> staff=new ArrayList<>(Arrays.asList(a,b,c));
        Department department = new Department("A", staff);
        System.out.println(department.totalMonthlyCost());
        System.out.println(a.annualCost());
    }
}
