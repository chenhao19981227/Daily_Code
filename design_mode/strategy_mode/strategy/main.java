package design_mode.strategy_mode.strategy;

public class main {
    public static void main(String[] args) {
        Duck mallardDuck=new MallardDuck();
        mallardDuck.fly();
        Duck rubberDuck=new RubberDuck();
        rubberDuck.fly();
    }
}
