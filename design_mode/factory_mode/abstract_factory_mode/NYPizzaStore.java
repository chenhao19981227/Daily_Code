package design_mode.factory_mode.abstract_factory_mode;

import design_mode.factory_mode.example.CheesePizza;

public class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza=null;
        PizzaIngredientFactory ingredientFactory=new NYPizzaIngredientFactory();
        if(type.equals("cheese")) {
            pizza=new NYStyleCheesePizza(ingredientFactory);
        }
        return pizza;
    }
}
