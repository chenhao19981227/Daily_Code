package reconstruction.chapter12.extract_superclass.before;

public class Employee {
    String name;
    int id;
    double monthlyCost;

    public Employee(String name, int id, double monthlyCost) {
        this.name = name;
        this.id = id;
        this.monthlyCost = monthlyCost;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }
    public double annualCost(){
        return this.monthlyCost*12;
    }
}
