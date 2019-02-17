package pl.lodz.p.edu.api.yahoo;

public enum ConditionCodesEnum {

    TORNADO(0L, "tornado"),
    TROPICAL_STORM(1L, "tropical storm"),
    HURRICANE(2L, "hurricane"),
    SEVERE_THUNDERSTORMS(3L, "severe thunderstorms"),
    THUNDERSTORMS(4L, "thunderstorms"),
    MIXED_RAIN_AND_SNOW(5L, "mixed rain and snow"),
    MIXED_RAIN_AND_SLEET(6L, "mixed rain and sleet"),
    MIXED_SNOW_AND_SLEET(7L, "mixed snow and sleet"),
    FREEZING_DRIZZLE(8L, "freezing drizzle"),
    DRIZZLE(9L, "drizzle"),
    FREEZING_RAIN(10L, "freezing rain");

    private Long code;
    private String description;

    ConditionCodesEnum(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
