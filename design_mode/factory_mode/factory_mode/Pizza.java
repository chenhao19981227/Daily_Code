package design_mode.factory_mode.factory_mode;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    List toppings=new ArrayList();
    void prepare(){
        System.out.println("Preparing "+name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings: ");
        for (Object topping : toppings) {
            System.out.println("   "+topping);
        }
    }
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
