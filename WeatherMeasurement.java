package algo.weatherdata;

import java.time.LocalDate;
import java.time.LocalTime;

public class WeatherMeasurement {
    private LocalDate date;
    private LocalTime time;
    private double temperature;
    private boolean confirmed;

    public WeatherMeasurement(LocalDate date, LocalTime time, double temperature, boolean confirmed) {
        this.date = date;
        this.time = time;
        this.temperature = temperature;
        this.confirmed = confirmed;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}