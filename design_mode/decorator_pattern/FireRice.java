package design_mode.decorator_pattern;

public class FireRice extends FastFood{
    public FireRice() {
        super(10,"fire rice");
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
