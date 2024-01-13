package design_mode.strategy_mode.strategy;

public class MallardDuck extends Duck {
    public MallardDuck(){
        flyBehavior=new FlyWithWings();
    }
    @Override
    public void display() {
        System.out.println("i am mallardDuck");
    }
}
