package design_mode.decorator_mode.decorator_mode;

public abstract class Beverage {
    String description ="Unknown Beverage";
    public String getDescription(){
        return description;
    }
    public abstract double cost();
}
