package design_mode.builder_pattern.example1;

public class ABikeBuilder extends BikeBuilder {
    @Override
    public void makeFrame() {
        bike.setFrame("a_frame");
    }

    @Override
    public void makeSeat() {
        bike.setSeat("a_frame");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}
