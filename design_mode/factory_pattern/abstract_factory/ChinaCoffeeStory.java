package design_mode.factory_pattern.abstract_factory;

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

    @Override
    public Cake makeCake() {
        return coffeeFactory.makeCake();
    }
}
