package design_mode.factory_mode.abstract_factory_mode;

public class ChicagoStylePizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza=null;
        PizzaIngredientFactory ingredientFactory=new ChicagoIngredientFactory();
        if(type.equals("cheese")){
            pizza=new ChicagoStyleCheesePizza(ingredientFactory);
        }
        return pizza;
    }
}
