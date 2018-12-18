package edu.p.lodz.pl.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item {

    @PrimaryKey
    private int id;

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

    public Item(int id, String name, double maxTemp, double minTemp, WeatherEnum weather, ActivityEnum activity) {
        this.id = id;
        this.name = name;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.weather = weather;
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static Item[] populateData() {
        return new Item[]{
                new Item(1, "Passport", 0.0D, 0.0D, null, null),
                new Item(2, "Money", 0.0D, 0.0D, null, null),
                new Item(3, "ID Card", 0.0D, 0.0D, null, null)
        };
    }
}
