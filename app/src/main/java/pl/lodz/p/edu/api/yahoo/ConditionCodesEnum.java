package pl.lodz.p.edu.api.yahoo;

import pl.lodz.p.edu.database.entity.WeatherEnum;

public enum ConditionCodesEnum {

    TORNADO(0L, "tornado", WeatherEnum.WINDY),
    TROPICAL_STORM(1L, "tropical storm", WeatherEnum.STORMY),
    HURRICANE(2L, "hurricane", WeatherEnum.WINDY),
    SEVERE_THUNDERSTORMS(3L, "severe thunderstorms", WeatherEnum.STORMY),
    THUNDERSTORMS(4L, "thunderstorms", WeatherEnum.STORMY),
    MIXED_RAIN_AND_SNOW(5L, "mixed rain and snow", WeatherEnum.RAINY),
    MIXED_RAIN_AND_SLEET(6L, "mixed rain and sleet", WeatherEnum.RAINY),
    MIXED_SNOW_AND_SLEET(7L, "mixed snow and sleet", WeatherEnum.RAINY),
    FREEZING_DRIZZLE(8L, "freezing drizzle", WeatherEnum.COLD),
    DRIZZLE(9L, "drizzle", WeatherEnum.COLD),
    FREEZING_RAIN(10L, "freezing rain", WeatherEnum.RAINY),
    SHOWERS_1(11L, "showers", WeatherEnum.RAINY),
    SHOWERS_2(12L, "showers", WeatherEnum.RAINY),
    SNOW_FLURRIES(13L, "snow flurries", WeatherEnum.SNOWY),
    LIGHT_SNOW_FLURRIES(14L, "light snow flurries", WeatherEnum.SNOWY),
    BLOWING_SNOW(15L, "blowing snow", WeatherEnum.SNOWY),
    SNOW(16L, "snow", WeatherEnum.SNOWY),
    HAIL(17L, "hail", WeatherEnum.RAINY),
    SLEET(18L, "sleet", WeatherEnum.RAINY),
    DUST(19L, "dust", WeatherEnum.WINDY),
    FOGGY(20L, "foggy", WeatherEnum.WINDY),
    HAZE(21L, "haze", WeatherEnum.WINDY),
    SMOKY(22L, "smoky", WeatherEnum.WINDY),
    BLUSTERY(23L, "blustery", WeatherEnum.RAINY),
    WINDY(24L, "windy", WeatherEnum.WINDY),
    COLD(25L, "cold", WeatherEnum.COLD),
    CLOUDY(26L, "cloudy", WeatherEnum.CLOUDY),
    MOSTLY_CLOUDY_NIGHT(27L, "mostly cloudy (night)", WeatherEnum.CLOUDY),
    MOSTLY_CLOUDY_DAY(28L, "mostly cloudy (day)", WeatherEnum.CLOUDY),
    PARTLY_CLOUDY_NIGHT(29L, "partly cloudy (night)", WeatherEnum.CLOUDY),
    PARTLY_CLOUDY_DAY(30L, "partly cloudy (day)", WeatherEnum.CLOUDY),
    CLEAR_NIGHT(31L, "clear (night)", WeatherEnum.SUNNY),
    SUNNY(32L, "sunny", WeatherEnum.SUNNY),
    FAIR_NIGHT(33L, "fair (night)", WeatherEnum.SUNNY),
    FAIR_DAY(34L, "fair (day)", WeatherEnum.SUNNY),
    MIXED_RAIN_AND_HAIL(35L, "mixed rain and hail", WeatherEnum.RAINY),
    HOT(36L, "hot", WeatherEnum.SUNNY),
    ISOLATED_THUNDERSTORMS(37L, "isolated thunderstorms", WeatherEnum.STORMY),
    SCATTERED_THUNDERSTORMS_1(38L, "scattered thunderstorms", WeatherEnum.STORMY),
    SCATTERED_THUNDERSTORMS_2(39L, "scattered thunderstorms", WeatherEnum.STORMY),
    SCATTERED_SHOWERS(40L, "scattered showers", WeatherEnum.RAINY),
    HEAVY_SNOW_1(41L, "heavy snow", WeatherEnum.SNOWY),
    SCATTERED_SNOW_SHOWERS(42L, "scattered snow showers", WeatherEnum.RAINY),
    HEAVY_SNOW_2(43L, "heavy snow", WeatherEnum.SNOWY),
    PARTLY_CLOUDY(44L, "partly cloudy", WeatherEnum.CLOUDY),
    THUNDERSHOWERS(45L, "thundershowers", WeatherEnum.STORMY),
    SNOW_SHOWERS(46L, "snow showers", WeatherEnum.RAINY),
    ISOLATED_THUNDERSHOWERS(47L, "isolated thundershowers", WeatherEnum.STORMY),
    NOT_AVAILABLE(3200L, "not available", null);

    private Long code;
    private String description;
    private WeatherEnum weather;

    ConditionCodesEnum(Long code, String description, WeatherEnum weather) {
        this.code = code;
        this.description = description;
        this.weather = weather;
    }

    public static ConditionCodesEnum getByCode(final Long code) {
        for (ConditionCodesEnum conditionCodes : ConditionCodesEnum.values()) {
            if (conditionCodes.code.equals(code)) {
                return conditionCodes;
            }
        }
        return null;
    }

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public WeatherEnum getWeather() {
        return weather;
    }
}
