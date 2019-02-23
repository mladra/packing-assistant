package pl.lodz.p.edu.database.entity.definitions;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import pl.lodz.p.edu.database.entity.ActivityEnum;
import pl.lodz.p.edu.database.entity.WeatherEnum;
import pl.lodz.p.edu.view.model.NewItemDataModel;

@Entity(tableName = "items_definitions")
public class ItemDefinition extends BaseEntity implements Serializable {

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "max_temp")
    private Double maxTemp;

    @ColumnInfo(name = "min_temp")
    private Double minTemp;

    @ColumnInfo(name = "weight")
    private Double weight;

    @ColumnInfo(name = "weather")
    private WeatherEnum weather;

    @ColumnInfo(name = "activity")
    private ActivityEnum activity;

    @Ignore
    public ItemDefinition() {}

    @Ignore
    public ItemDefinition(String name) {
        this.name = name;
        this.weight = 1.0D;
    }

    @Ignore
    public ItemDefinition(String name, ActivityEnum activity) {
        this(name);
        this.activity = activity;
    }

    @Ignore
    public ItemDefinition(String name, ActivityEnum activity, Double minTemp, Double maxTemp) {
        this(name, activity);
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    @Ignore
    public ItemDefinition(String name, ActivityEnum activity, WeatherEnum weather) {
        this(name, activity);
        this.weather = weather;
    }

    public ItemDefinition(String name, Double maxTemp, Double minTemp, Double weight, WeatherEnum weather, ActivityEnum activity) {
        this(name, activity, minTemp, maxTemp);
        this.weather = weather;
        this.weight = weight;
    }

    @Ignore
    public ItemDefinition(Long id, String name, Double maxTemp, Double minTemp, Double weight, WeatherEnum weather, ActivityEnum activity) {
        this(name, maxTemp, minTemp, weight, weather, activity);
        this.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
                new ItemDefinition("Passport"),
                new ItemDefinition("Money"),
                new ItemDefinition("ID Card"),
                new ItemDefinition("Bike shoes", ActivityEnum.RIDING_A_BIKE),
                new ItemDefinition("Running shoes", ActivityEnum.JOGGING),
                new ItemDefinition("Basketball shoes", ActivityEnum.PLAYING_BASKETBALL),
                new ItemDefinition("Football shoes", ActivityEnum.PLAYING_FOOTBALL),
                new ItemDefinition("Swimming trunks", ActivityEnum.SWIMMING),
                new ItemDefinition("Briefcase", ActivityEnum.OTHER, 5.0D, 15.0D),
                new ItemDefinition("Briefcase 2", ActivityEnum.OTHER, 16.0D, 25.D),
                new ItemDefinition("Briefcase 3", ActivityEnum.OTHER, 26.0D, 35.0D),
                new ItemDefinition("Briefcase 4", ActivityEnum.OTHER, 36.0D, 45.0D),
                new ItemDefinition("Briefcase 5", ActivityEnum.OTHER, -0.6D, 4.0D),
                new ItemDefinition("For Sunny", ActivityEnum.OTHER, WeatherEnum.SUNNY),
                new ItemDefinition("For Windy", ActivityEnum.OTHER, WeatherEnum.WINDY),
                new ItemDefinition("For Snowy", ActivityEnum.OTHER, WeatherEnum.SNOWY),
                new ItemDefinition("For Stormy", ActivityEnum.OTHER, WeatherEnum.STORMY),
                new ItemDefinition("For Rainy", ActivityEnum.OTHER, WeatherEnum.RAINY)
        };
    }

    public static ItemDefinition of(NewItemDataModel model) {
        return new ItemDefinition(
                model.getId(),
                model.getName(),
                model.getMaxTemp(),
                model.getMinTemp(),
                model.getWeight(),
                model.getWeather(),
                model.getActivity() == null ? ActivityEnum.OTHER : model.getActivity()
        );
    }
}
