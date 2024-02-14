package reconstruction.chapter10.replace_conditional_with_polymorphism.example1.after;

public class AfricanSwallow extends Bird{
    public AfricanSwallow(Bird bird) {
        super(bird);
    }
    public String plumage() {
        return (this.numberOfCoconuts > 2) ? "tired" : "average";
    }
    public double airSpeedVelocity(){
        return 40-2*this.numberOfCoconuts;
    }
}
