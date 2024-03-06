package design_mode.factory_pattern.example;

public class AmericaCoffeeStory implements CoffeeStory{
    @Override
    public Coffee makeCoffee(String coffeeName) {
        Coffee coffee=null;
        if("ACoffee".equals(coffeeName)){
            coffee=new ACoffee();
        }else if("BCoffee".equals(coffeeName)){
            coffee=new BCoffee();
        }
        assert coffee != null;
        coffee.bake();
        coffee.addSugar();
        System.out.println(coffeeName+" done");
        return coffee;
    }
}
