package design_mode.decorator_mode.decorator_mode;

public class test {
    public static void main(String[] args) {
        Beverage beverage=new Espresso();
        beverage=new Whip(beverage);
        System.out.println(beverage.getDescription()+" $"+beverage.cost());

        Beverage beverage2=new DarkRoast();
        beverage2=new Mocha(beverage2);
        beverage2=new Mocha(beverage2);
        beverage2=new Whip(beverage2);
        System.out.println(beverage2.getDescription()+" $"+beverage2.cost());
    }
}
