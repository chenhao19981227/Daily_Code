package reconstruction.chapter11.preserve_whole_object.after;

public class After {
    public static void main(String[] args) {
        TemperatureRange temperatureRange=new TemperatureRange(100,0);
        HeatingPlan heatingPlan=new HeatingPlan(new TemperatureRange(90,-100));
        if(!heatingPlan.withRange(temperatureRange)) System.out.println("alert");
    }
}
