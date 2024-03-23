package design_mode.decorator_pattern;

public class FriedNoodles extends FastFood{
    public FriedNoodles(){
        super(20,"fire noodles");
    }
    @Override
    public float cost() {
        return getPrice();
    }
}
