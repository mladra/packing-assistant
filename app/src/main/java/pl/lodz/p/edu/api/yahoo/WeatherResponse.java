package pl.lodz.p.edu.api.yahoo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    private Location location;

    @SerializedName("current_observation")
    private CurrentObservation currentObservation;

    private List<Forecast> forecasts;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
