package edu.p.lodz.pl.database.entity;

public enum ActivityEnum {

    RIDING_A_BIKE("RIDING_A_BIKE", "Riding a bike"),
    SWIMMING("SWIMMING", "Swimming"),
    PLAYING_GOLF("PLAYING_GOLF", "Playing golf"),
    PLAYING_FOOTBALL("PLAYING_FOOTBALL", "Playinh football"),
    PLAYING_BASKETBALL("PLAYING_BASKETBALL", "Playing basketball"),
    JOGGING("JOGGING", "Jogging"),
    SKATING("SKATING", "Skating"),
    SKIING("SKIING", "Skiing"),
    SNOWBOARDING("SNOWBOARDING", "Snowboarding");

    private String code;
    private String name;

    ActivityEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
