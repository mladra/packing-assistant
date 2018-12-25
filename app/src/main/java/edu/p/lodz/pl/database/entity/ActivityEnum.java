package edu.p.lodz.pl.database.entity;

import edu.p.lodz.pl.R;

public enum ActivityEnum {

    RIDING_A_BIKE("RIDING_A_BIKE", "Riding a bike", R.drawable.ic_bike_white_24dp),
    SWIMMING("SWIMMING", "Swimming",R.drawable.ic_swim_white_24dp),
    PLAYING_GOLF("PLAYING_GOLF", "Playing golf",R.drawable.ic_golf_white_24dp),
    PLAYING_FOOTBALL("PLAYING_FOOTBALL", "Playing football",R.drawable.ic_soccer_white_24dp),
    PLAYING_BASKETBALL("PLAYING_BASKETBALL", "Playing basketball", R.drawable.ic_basketball_white_24dp),
    JOGGING("JOGGING", "Jogging", R.drawable.ic_jogging_white_24dp),
    SKATING("SKATING", "Skating", R.drawable.ic_questionmark_white_24dp),
    SKIING("SKIING", "Skiing", R.drawable.ic_questionmark_white_24dp),
    SNOWBOARDING("SNOWBOARDING", "Snowboarding", R.drawable.ic_questionmark_white_24dp);

    private String code;
    private String name;
    private int iconResourceId;

    ActivityEnum(String code, String name, int iconResourceId) {
        this.code = code;
        this.name = name;
        this.iconResourceId = iconResourceId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }
}
