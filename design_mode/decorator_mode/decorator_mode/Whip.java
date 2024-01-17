package design_mode.decorator_mode.decorator_mode;

public class Whip extends CondimentDecorator{
    Beverage beverage;
    public Whip(Beverage beverage){
        this.beverage=beverage;
    }
    @Override
    public double cost() {
        return 1.2+beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Whip";
    }
}
