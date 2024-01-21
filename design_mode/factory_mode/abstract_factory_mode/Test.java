package design_mode.factory_mode.abstract_factory_mode;

import design_mode.factory_mode.factory_mode.ChicagoStylePizzaStore;
import design_mode.factory_mode.factory_mode.NYPizzaStore;
import design_mode.factory_mode.factory_mode.Pizza;
import design_mode.factory_mode.factory_mode.PizzaStore;

public class Test {
    public static void main(String[] args) {
        design_mode.factory_mode.factory_mode.PizzaStore nyPizzaStore=new NYPizzaStore();
        design_mode.factory_mode.factory_mode.Pizza nyStyleCheesePizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("--------------------");
        PizzaStore chicagoPizzaStyle=new ChicagoStylePizzaStore();
        Pizza chicagoStyleCheesePizza = chicagoPizzaStyle.orderPizza("cheese");
    }
}
