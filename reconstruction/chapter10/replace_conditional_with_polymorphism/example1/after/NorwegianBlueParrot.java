package reconstruction.chapter10.replace_conditional_with_polymorphism.example1.after;

public class NorwegianBlueParrot extends Bird {
    public NorwegianBlueParrot(Bird bird) {
        super(bird);
    }
    public String plumage() {
        return (this.voltage > 100) ? "scorched" : "beautiful";
    }
    public double airSpeedVelocity(){
        return (this.isNailed)?0:10+this.voltage/10;
    }
}
