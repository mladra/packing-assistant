package pl.lodz.p.edu.view.model;

import java.util.Date;
import java.util.List;

import androidx.databinding.ObservableField;
import pl.lodz.p.edu.database.entity.ActivityEnum;
import pl.lodz.p.edu.database.entity.WeatherEnum;
import pl.lodz.p.edu.database.entity.definitions.PackingListDefinition;

public class PackingListCreationParameters {

    private String cityName;
    private double minTemp;
    private double maxTemp;
    private WeatherEnum weather;
    private List<ActivityEnum> activities;
    private Date[] rangeDate = new Date[2];
    private boolean fromTemplate;
    private PackingListDefinition template;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

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

    public Date[] getRangeDate() {
        return rangeDate;
    }

    public void setRangeDate(Date[] rangeDate) {
        this.rangeDate = rangeDate;
    }

    public PackingListDefinition getTemplate() {
        return template;
    }

    public void setTemplate(PackingListDefinition template) {
        this.template = template;
    }

    public boolean isFromTemplate() {
        return fromTemplate;
    }

    public void setFromTemplate(boolean fromTemplate) {
        this.fromTemplate = fromTemplate;
    }
}
