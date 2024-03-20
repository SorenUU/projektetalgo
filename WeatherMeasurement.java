package algo.weatherdata;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class for weather measurements
 * @author olivergottberg, sorenbeigi
 */
public class WeatherMeasurement {
    private final LocalTime time;
    private final LocalDate date;
    private final double temperature;
    private final boolean confirmed;
    public WeatherMeasurement(LocalDate date, LocalTime time, double temperature, boolean
            confirmed) {
        this.date = date;
        this.temperature = temperature;
        this.confirmed = confirmed;
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }
    public boolean isConfirmed() {
        return confirmed;
    }
}