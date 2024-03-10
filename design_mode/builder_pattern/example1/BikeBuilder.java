package design_mode.builder_pattern.example1;

public abstract class BikeBuilder {
    protected Bike bike =new Bike();
    public abstract void makeFrame();
    public abstract void makeSeat();
    public abstract Bike createBike();
}
