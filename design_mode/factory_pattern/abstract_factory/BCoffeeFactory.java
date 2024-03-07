package design_mode.factory_pattern.abstract_factory;

public class BCoffeeFactory implements CoffeeFactory {
    @Override
    public Coffee makeCoffee() {
        return new BCoffee();
    }

    @Override
    public Cake makeCake() {
        return new BCake();
    }
}
