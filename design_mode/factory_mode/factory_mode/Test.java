package design_mode.factory_mode.factory_mode;

public class Test {
    public static void main(String[] args) {
        PizzaStore nyPizzaStore=new NYPizzaStore();
        Pizza nyStyleCheesePizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Order a "+nyStyleCheesePizza.name);

        System.out.println("--------------------");

        PizzaStore chicagoPizzaStyle=new ChicagoStylePizzaStore();
        Pizza chicagoStyleCheesePizza = chicagoPizzaStyle.orderPizza("cheese");
        System.out.println("Order a "+chicagoStyleCheesePizza.name);
    }
}
