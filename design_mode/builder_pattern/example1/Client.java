package design_mode.builder_pattern.example1;

public class Client {
    public static void main(String[] args) {
        BikeBuilder aBikeBuilder=new ABikeBuilder();
        BBikeBuilder bBikeBuilder = new BBikeBuilder();
        showBike(aBikeBuilder);
        showBike(bBikeBuilder);
    }
    private static void showBike(BikeBuilder bikeBuilder){
        Director director=new Director(bikeBuilder);
        Bike bike = director.constructor();
        System.out.println(bike);
    }
}
