package design_mode.factory_mode.factory_mode;

public class ChicagoStylePizzaStore extends PizzaStore{
    @Override
    Pizza createPizza(String type) {
        if(type.equals("cheese")){
            return new ChicagoStyleCheesePizza();
        }else {
            return null;
        }
    }
}
