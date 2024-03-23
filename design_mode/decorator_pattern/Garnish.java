package design_mode.decorator_pattern;

public abstract class Garnish extends FastFood{
    private FastFood fastFood;
    public Garnish(FastFood fastFood,float price, String desc) {
        super(price, desc);
        this.fastFood=fastFood;
    }

    public FastFood getFastFood() {
        return fastFood;
    }

    public void setFastFood(FastFood fastFood) {
        this.fastFood = fastFood;
    }

    @Override
    public float cost() {
        return fastFood.getPrice()+getPrice();
    }
    @Override
    public String getDesc() {
        return getFastFood().getDesc()+" "+super.getDesc();
    }
}
