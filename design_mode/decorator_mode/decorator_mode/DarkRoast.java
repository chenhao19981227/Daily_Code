package design_mode.decorator_mode.decorator_mode;

public class DarkRoast extends Beverage{
    public DarkRoast(){
        description="DarkRoast";
    }
    @Override
    public double cost() {
        return 3.99;
    }
}
