package design_mode.factory_pattern.simple_factory;

public class ChinaCoffeeStory implements CoffeeStory {
    @Override
    public Coffee makeCoffee(String coffeeName) {
        return CoffeeFactory.makeCoffee(coffeeName);
    }
}
