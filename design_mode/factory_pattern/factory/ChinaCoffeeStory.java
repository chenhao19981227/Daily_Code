package design_mode.factory_pattern.factory;

public class ChinaCoffeeStory implements CoffeeStory {
    private CoffeeFactory coffeeFactory;
    public ChinaCoffeeStory(CoffeeFactory coffeeFactory) {
        this.coffeeFactory = coffeeFactory;
    }
    @Override
    public Coffee makeCoffee() {
        Coffee coffee=null;
        coffee=coffeeFactory.makeCoffee();
        coffee.bake();
        coffee.addSugar();
        return coffee;
    }
}
