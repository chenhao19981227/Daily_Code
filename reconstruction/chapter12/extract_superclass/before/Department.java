package reconstruction.chapter12.extract_superclass.before;

import java.util.List;
import java.util.Optional;

public class Department {
    String name;
    List<Employee> staff;

    public Department(String name, List<Employee> staff) {
        this.name = name;
        this.staff = staff;
    }

    public List<Employee> getStaff() {
        return staff;
    }

    public String getName() {
        return name;
    }
    public double totalMonthlyCost(){
        Optional<Double> sum = staff.stream()
                .map(staff -> staff.monthlyCost)
                .reduce(Double::sum);
        return sum.orElseGet(sum::get);
    }
    public int headCount(){
        return this.staff.size();
    }
    public double totalAnnualCost(){
        return totalMonthlyCost()*12;
    }
}
