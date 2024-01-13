package design_mode.strategy_mode.strategy;

public class RubberDuck extends Duck {
    public RubberDuck(){
        flyBehavior=new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("i am RubberDuck");
    }
}
