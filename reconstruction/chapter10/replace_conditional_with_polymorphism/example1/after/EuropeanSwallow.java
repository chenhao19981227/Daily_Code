package reconstruction.chapter10.replace_conditional_with_polymorphism.example1.after;

public class EuropeanSwallow extends Bird{
    public EuropeanSwallow(Bird bird) {
        super(bird);
    }

    public String plumage() {
        return "average";
    }
    public double airSpeedVelocity(){
        return 35;
    }
}
