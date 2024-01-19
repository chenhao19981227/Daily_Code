package design_mode.factory_mode.simpleFactory;

public class PizzaShop {
    // 假设我们有一家pizza店，店里能做很多种pizza，那我们在生产时，要如何确定要做的pizza类型？
    public Pizza orderPizza(String type){
        Pizza pizza;
        if(type.equals("cheese")){
            pizza=new CheesePizza();
        }else {
            pizza = new GreekPizza();
        }
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    // 可以看到，如果pizza类型发生改变，比如新增一种口味或者减少一种口味，代码都得修改。
}
