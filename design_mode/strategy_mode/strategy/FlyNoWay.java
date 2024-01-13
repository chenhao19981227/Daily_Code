package design_mode.strategy_mode.strategy;

public class FlyNoWay implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("i can't fly");
    }
}
