package edu.p.lodz.pl.database.entity.definitions;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import edu.p.lodz.pl.database.entity.ActivityEnum;
import edu.p.lodz.pl.database.entity.WeatherEnum;

@Entity(tableName = "items_definitions")
public class ItemDefinition extends BaseEntity {

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "max_temp")
    private double maxTemp;

    @ColumnInfo(name = "min_temp")
    private double minTemp;

    @ColumnInfo(name = "weather")
    private WeatherEnum weather;

    @ColumnInfo(name = "activity")
    private ActivityEnum activity;

    public ItemDefinition(String name, double maxTemp, double minTemp, WeatherEnum weather, ActivityEnum activity) {
        this.name = name;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.weather = weather;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public WeatherEnum getWeather() {
        return weather;
    }

    public void setWeather(WeatherEnum weather) {
        this.weather = weather;
    }

    public ActivityEnum getActivity() {
        return activity;
    }

    public void setActivity(ActivityEnum activity) {
        this.activity = activity;
    }

    public static ItemDefinition[] populateData() {
        return new ItemDefinition[]{
                new ItemDefinition("Passport", 0.0D, 0.0D, null, null),
                new ItemDefinition("Money", 0.0D, 0.0D, null, null),
                new ItemDefinition("ID Card", 0.0D, 0.0D, null, null),
                new ItemDefinition("Bike shoes", 0.0D, 0.0D, null, ActivityEnum.RIDING_A_BIKE),
                new ItemDefinition("Running shoes", 0.0D, 0.0D, null, ActivityEnum.JOGGING),
                new ItemDefinition("Basketball shoes", 0.0D, 0.0D, null, ActivityEnum.PLAYING_BASKETBALL),
                new ItemDefinition("Football shoes", 0.0D, 0.0D, null, ActivityEnum.PLAYING_FOOTBALL),
                new ItemDefinition("Swimming trunks", 0.0D, 0.0D, null, ActivityEnum.SWIMMING)
        };
    }
}
