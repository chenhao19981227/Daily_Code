package design_mode.decorator_pattern;

public class Client {
    public static void main(String[] args) {
        FastFood food=new FireRice();
        System.out.println(food.getDesc() + " " + food.cost() + "元");

        FastFood eggFood=new FireRice();
        eggFood=new Egg(eggFood);
        System.out.println(eggFood.getDesc() + " " + eggFood.cost() + "元");

        FastFood baconNoodles = new FriedNoodles();
        baconNoodles = new Bacon(baconNoodles);
        //花费的价格
        System.out.println(baconNoodles.getDesc() + " " + baconNoodles.cost() + "元");
    }
}
