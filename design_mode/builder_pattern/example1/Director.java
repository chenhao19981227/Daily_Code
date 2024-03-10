package design_mode.builder_pattern.example1;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Director {
    private BikeBuilder bikeBuilder;
    public Bike constructor(){
        bikeBuilder.makeFrame();
        bikeBuilder.makeSeat();
        return bikeBuilder.createBike();
    }
}
