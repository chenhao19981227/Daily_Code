package design_mode.observer_mode.observer;

import java.util.ArrayList;

public class WeatherData implements Subject{
    private ArrayList<Observer> observers;
    private float temp;
    private float humidity;
    private float pressure;
    public WeatherData(){
        observers=new ArrayList<>();
    }
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temp,humidity,pressure);
        }
    }
    public void measurementsChanged(){
        notifyObservers();
    }
    public void seatMeasurements(float temp,float humidity,float pressure){
        this.temp=temp;
        this.humidity=humidity;
        this.pressure=pressure;
        measurementsChanged();
    }
}
