package reconstruction.chapter11.preserve_whole_object.before;

public class Before {
    public static void main(String[] args) {
        TemperatureRange temperatureRange=new TemperatureRange(100,0);
        HeatingPlan heatingPlan=new HeatingPlan(new TemperatureRange(90,-100));
        double high = temperatureRange.getHigh();
        double low = temperatureRange.getLow();
        if(!heatingPlan.withRange(high,low)) System.out.println("alert");
    }
}
