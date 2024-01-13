package design_mode.strategy_mode.strategy;

public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    public Duck(){};
    // 将会变化的行为抽取为接口
    public void quack(){
        quackBehavior.quack();// 只需委托给别人
    };
    public void swim(){
        System.out.println("swim");
    };
    public void fly(){
        flyBehavior.fly();
    };
    public abstract void display();
}
