package design_mode.strategy_mode.base;

public class RubberDuck extends Duck{
    // 橡皮鸭不会飞，只能覆盖父类的fly方法
    // 那如果每次引入新的品种，或者新的行为都需要修改代码，也太麻烦了
    @Override
    public void fly() {
        System.out.println("i can't fly");
    }

    @Override
    public void display() {
        System.out.println("i am RubberDuck");
    }
}
