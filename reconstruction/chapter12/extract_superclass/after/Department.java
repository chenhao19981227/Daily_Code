package reconstruction.chapter12.extract_superclass.after;

import java.util.List;
import java.util.Optional;

public class Department extends Party{
    List<Employee> staff;
    public Department(String name, List<Employee> staff) {
        super(name);
        this.staff = staff;
    }
    public List<Employee> getStaff() {
        return staff;
    }

    @Override
    public double monthlyCost() {
        return totalMonthlyCost();
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
}
