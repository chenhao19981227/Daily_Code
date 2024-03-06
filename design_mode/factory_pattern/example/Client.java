package design_mode.factory_pattern.example;

public class Client {
    public static void main(String[] args) {
        CoffeeStory aCoffeeStory=new AmericaCoffeeStory();
        Coffee aCoffee = aCoffeeStory.makeCoffee("ACoffee");
    }
}
