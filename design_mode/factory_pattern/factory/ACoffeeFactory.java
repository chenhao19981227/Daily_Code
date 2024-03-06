package design_mode.factory_pattern.factory;

public class ACoffeeFactory implements CoffeeFactory{
    @Override
    public Coffee makeCoffee(){
        return new ACoffee();
    }
}
