package design_mode.strategy_mode.strategy;

public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("i can fly with wings");
    }
}
