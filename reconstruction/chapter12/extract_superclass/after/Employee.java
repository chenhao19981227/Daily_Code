package reconstruction.chapter12.extract_superclass.after;

public class Employee extends Party{
    int id;
    double monthlyCost;

    public Employee(String name, int id, double monthlyCost) {
        super(name);
        this.id = id;
        this.monthlyCost = monthlyCost;
    }

    public int getId() {
        return id;
    }

    @Override
    public double monthlyCost() {
        return this.monthlyCost;
    }
}
