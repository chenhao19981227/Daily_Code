package design_mode.factory_mode.simpleFactory;

import design_mode.factory_mode.example.CheesePizza;
import design_mode.factory_mode.example.GreekPizza;
import design_mode.factory_mode.example.Pizza;

public class SimplePizzaFactory {
    public Pizza createPizza(String type){
        Pizza pizza=null;
        if(type.equals("cheese")){
            pizza=new CheesePizza();
        }else if(type.equals("greek")){
            pizza = new GreekPizza();
        }
        return pizza;
    }
}
