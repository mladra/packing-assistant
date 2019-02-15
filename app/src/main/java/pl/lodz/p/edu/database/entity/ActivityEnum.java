package pl.lodz.p.edu.database.entity;

import pl.lodz.p.edu.R;

public enum ActivityEnum {

    GENERAL("GENERAL", "General", R.drawable.ic_questionmark_white_24dp, false),
    OTHER("OTHER", "Other", R.drawable.ic_questionmark_white_24dp, false),
    RIDING_A_BIKE("RIDING_A_BIKE", "Riding a bike", R.drawable.ic_bike_white_24dp, true),
    SWIMMING("SWIMMING", "Swimming",R.drawable.ic_swim_white_24dp, true),
    PLAYING_GOLF("PLAYING_GOLF", "Playing golf",R.drawable.ic_golf_white_24dp, true),
    PLAYING_FOOTBALL("PLAYING_FOOTBALL", "Playing football",R.drawable.ic_soccer_white_24dp, true),
    PLAYING_BASKETBALL("PLAYING_BASKETBALL", "Playing basketball", R.drawable.ic_basketball_white_24dp, true),
    JOGGING("JOGGING", "Jogging", R.drawable.ic_jogging_white_24dp, true),
    SKATING("SKATING", "Skating", R.drawable.ic_questionmark_white_24dp, true),
    SKIING("SKIING", "Skiing", R.drawable.ic_questionmark_white_24dp, true),
    SNOWBOARDING("SNOWBOARDING", "Snowboarding", R.drawable.ic_questionmark_white_24dp, true);

    private String code;
    private String name;
    private int iconResourceId;
    private boolean activity;

    ActivityEnum(String code, String name, int iconResourceId, boolean activity) {
        this.code = code;
        this.name = name;
        this.iconResourceId = iconResourceId;
        this.activity = activity;
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

    public boolean isActivity() {
        return activity;
    }

    public static ActivityEnum getByDisplayName(String displayName) {
        for (ActivityEnum value : ActivityEnum.values()) {
            if (value.getName().equals(displayName)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }
}
