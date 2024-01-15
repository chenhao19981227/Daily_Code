package design_mode.observer_mode.observer;

public class test {
    public static void main(String[] args) {
        WeatherData weatherData=new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay=new CurrentConditionsDisplay(weatherData);
        weatherData.seatMeasurements(80,65,34.5f);
    }
}
