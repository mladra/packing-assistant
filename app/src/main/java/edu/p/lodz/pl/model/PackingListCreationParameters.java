package edu.p.lodz.pl.model;

import java.util.List;

import edu.p.lodz.pl.database.entity.ActivityEnum;
import edu.p.lodz.pl.database.entity.WeatherEnum;

public class PackingListCreationParameters {

    private double minTemp;
    private double maxTemp;
    private WeatherEnum weather;
    private List<ActivityEnum> activities;

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public WeatherEnum getWeather() {
        return weather;
    }

    public List<ActivityEnum> getActivities() {
        return activities;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setWeather(WeatherEnum weather) {
        this.weather = weather;
    }

    public void setActivities(List<ActivityEnum> activities) {
        this.activities = activities;
    }
}
