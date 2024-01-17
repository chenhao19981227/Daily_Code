package design_mode.decorator_mode.decorator_mode;

public abstract class CondimentDecorator extends Beverage{
    // 继承Beverage是为了装饰者也能被装饰，即可以添加多种调料
    public abstract String getDescription();
}
