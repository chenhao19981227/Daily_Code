package design_mode.factory_mode.example;

import design_mode.factory_mode.simpleFactory.SimplePizzaFactory;

public class PizzaShop {
    // 假设我们有一家pizza店，店里能做很多种pizza，那我们在生产时，要如何确定要做的pizza类型？
    SimplePizzaFactory simplePizzaFactory;
    public PizzaShop(SimplePizzaFactory simplePizzaFactory){
        this.simplePizzaFactory=simplePizzaFactory;
    }
    public Pizza orderPizza(String type){
        // 把new操作委托给工厂
        Pizza pizza=simplePizzaFactory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    // 其实这里做的是一种函数抽取，将公用代码抽取到一个类中，这样，当你有其他pizza店时，不需要重复创造pizza的代码，只需要引入同一个工厂类即可。
}
