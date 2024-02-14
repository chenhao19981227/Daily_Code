package reconstruction.chapter10.replace_conditional_with_polymorphism.example1.after;

public class Bird {
    String type;
    int numberOfCoconuts;
    double voltage;
    boolean isNailed;

    public Bird(String type, int numberOfCoconuts, double voltage, boolean isNailed) {
        this.type = type;
        this.numberOfCoconuts = numberOfCoconuts;
        this.voltage = voltage;
        this.isNailed = isNailed;
    }

    public Bird(Bird bird){
        this.type=bird.type;
        this.numberOfCoconuts= bird.numberOfCoconuts;
        this.voltage=bird.voltage;
        this.isNailed=bird.isNailed;
    }
    public String plumage() {
        return "unknown";
    }
    public double airSpeedVelocity(){
        return -1;
    }
}
