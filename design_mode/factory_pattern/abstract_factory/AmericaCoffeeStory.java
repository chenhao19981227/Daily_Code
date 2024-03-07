package design_mode.factory_pattern.abstract_factory;

public class AmericaCoffeeStory implements CoffeeStory {
    private CoffeeFactory coffeeFactory;

    public AmericaCoffeeStory(CoffeeFactory coffeeFactory) {
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
