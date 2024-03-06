package design_mode.factory_pattern.simple_factory;

public class Client {
    public static void main(String[] args) {
        CoffeeStory aCoffeeStory=new AmericaCoffeeStory();
        Coffee aCoffee = aCoffeeStory.makeCoffee("ACoffee");
    }
}
