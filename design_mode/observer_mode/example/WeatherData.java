package design_mode.observer_mode.example;

public class WeatherData {
    CurrentConditionsDisplay currentConditionsDisplay=new CurrentConditionsDisplay();
    StatisticsDisplay statisticsDisplay=new StatisticsDisplay();
    ForecastDisplay forecastDisplay=new ForecastDisplay();
    public void measurementChanged(){//三个指标发生变化时，获取新指标，更新布告板
        float temp=getTemperature();
        float humidity=getHumidity();
        float pressure=getPressure();
        currentConditionsDisplay.update(temp,humidity,pressure);
        statisticsDisplay.update(temp,humidity,pressure);
        forecastDisplay.update(temp,humidity,pressure);
    }

    private float getPressure() {
        return 0;
    }

    private float getHumidity() {
        return 0;
    }

    private float getTemperature() {
        return 0;
    }
}
