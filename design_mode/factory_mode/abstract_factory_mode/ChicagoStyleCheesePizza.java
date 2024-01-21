package design_mode.factory_mode.abstract_factory_mode;

public class ChicagoStyleCheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;
    public ChicagoStyleCheesePizza(PizzaIngredientFactory ingredientFactory){
        this.ingredientFactory=ingredientFactory;
    }

    @Override
    void prepare() {
        name="Chicago Style Cheese Pizza";
        System.out.println("Preparing "+name);
        dough=ingredientFactory.createDough();
        sauce=ingredientFactory.createSauce();
        cheese=ingredientFactory.createCheese();
    }

    void cut(){
        System.out.println("Cutting the pizza into square slices");
    }
}
