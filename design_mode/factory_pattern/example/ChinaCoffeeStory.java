package design_mode.factory_pattern.example;

public class ChinaCoffeeStory implements CoffeeStory{
    @Override
    public Coffee makeCoffee(String coffeeName) {
        Coffee coffee=null;
        if("ACoffee".equals(coffeeName)){
            coffee=new ACoffee();
        }else if("BCoffee".equals(coffeeName)){
            coffee=new BCoffee();
        }
        return coffee;
    }
}
