package design_mode.decorator_mode.example;

public class DarkRoast extends Beverage{
    // 每个种类的饮料都得继承饮料类，实现自己的价格方法
    // 那如果加入不同的调料，在这种设计下，只要调料发生变化就得创建一个新的类，就会导致类爆炸的局面
    @Override
    public void setDescription(String description) {
        description="超优深培";
    }
    @Override
    float cost() {
        return 10.0f;
    }
}
