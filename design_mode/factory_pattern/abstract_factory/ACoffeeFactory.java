package design_mode.factory_pattern.abstract_factory;

public class ACoffeeFactory implements CoffeeFactory {
    @Override
    public Coffee makeCoffee(){
        return new ACoffee();
    }

    @Override
    public Cake makeCake() {
        return new ACake();
    }
}
