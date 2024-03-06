package design_mode.factory_pattern.factory;

public class Client {
    public static void main(String[] args) {
        CoffeeStory aCoffeeStory=new AmericaCoffeeStory(new ACoffeeFactory());
        Coffee aCoffee = aCoffeeStory.makeCoffee();
    }
}
