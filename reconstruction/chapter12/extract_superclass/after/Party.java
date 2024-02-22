package reconstruction.chapter12.extract_superclass.after;

public class Party {
    String name;
    public Party(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }
    public double annualCost(){
        return this.monthlyCost()*12;
    }
    public double monthlyCost(){return 0.0;};
}
