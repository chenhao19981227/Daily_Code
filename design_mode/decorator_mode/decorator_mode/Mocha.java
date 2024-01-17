package design_mode.decorator_mode.decorator_mode;

public class Mocha extends CondimentDecorator{// CondimentDecorator本身也继承自Beverage
    Beverage beverage;// 对于装饰者来说，需要传入一个装饰对象
    public Mocha(Beverage beverage){
        this.beverage=beverage;
    }
    @Override
    public double cost() {
        return 0.20+beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Mocha";
    }
}
