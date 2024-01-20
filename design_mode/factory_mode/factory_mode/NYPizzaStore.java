package design_mode.factory_mode.factory_mode;

public class NYPizzaStore extends PizzaStore{
    @Override
    Pizza createPizza(String type) {
        if(type.equals("cheese")){
            return new NYStyleCheesePizza();
        }else {
            return null;
        }
    }
}
