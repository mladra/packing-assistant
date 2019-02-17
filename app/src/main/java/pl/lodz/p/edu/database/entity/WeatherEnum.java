package pl.lodz.p.edu.database.entity;

public enum WeatherEnum {

    SUNNY("Sunny"),
    WINDY("Windy"),
    CLOUDY("Cloudy"),
    RAINY("Rainy"),
    STORMY("Stormy"),
    SNOWY("Snowy"),
    COLD("Cold");

    private String displayName;

    WeatherEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static WeatherEnum getByDisplayName(String displayName) {
        for (WeatherEnum value : WeatherEnum.values()) {
            if (value.getDisplayName().equals(displayName)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
