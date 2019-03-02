package pl.lodz.p.edu.view.model;


import pl.lodz.p.edu.database.entity.ActivityEnum;
import pl.lodz.p.edu.database.entity.WeatherEnum;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;

public class NewItemDataModel {

    private Long id;
    private String name;
    private Double minTemp;
    private Double maxTemp;
    private Double weight;
    private WeatherEnum weather;
    private ActivityEnum activity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static NewItemDataModel of(final ItemDefinition itemDefinition) {
        final NewItemDataModel model = new NewItemDataModel();
        model.setId(itemDefinition.getId());
        model.setName(itemDefinition.getName());
        model.setMinTemp(itemDefinition.getMinTemp());
        model.setMaxTemp(itemDefinition.getMaxTemp());
        model.setWeight(itemDefinition.getWeight());
        model.setActivity(itemDefinition.getActivity());
        model.setActivity(itemDefinition.getActivity());
        return model;
    }
}
