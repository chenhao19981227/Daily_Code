package design_mode.factory_pattern.factory;

public class BCoffeeFactory implements CoffeeFactory{
    @Override
    public Coffee makeCoffee() {
        return new BCoffee();
    }
}
