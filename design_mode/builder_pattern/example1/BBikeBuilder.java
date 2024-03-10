package design_mode.builder_pattern.example1;

public class BBikeBuilder extends BikeBuilder{

    @Override
    public void makeFrame() {
        bike.setFrame("b_frame");
    }

    @Override
    public void makeSeat() {
        bike.setSeat("b_seat");
    }

    @Override
    public Bike createBike() {
        return bike;
    }
}
