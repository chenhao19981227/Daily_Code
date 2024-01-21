package design_mode.factory_mode.abstract_factory_mode;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Veggies veggies[];
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clams;
    abstract void prepare();
    void bake(){
        System.out.println("Bake for 25 minutes at 350");
    }
    void cut(){
        System.out.println("Cutting the pizza");
    }
    void box(){
        System.out.println("Place pizza in official PizzaStore box");
    }
    public String getName(){
        return name;
    }
}
