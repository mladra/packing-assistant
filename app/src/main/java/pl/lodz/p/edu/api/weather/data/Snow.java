package pl.lodz.p.edu.api.weather.data;

import com.google.gson.annotations.SerializedName;

public class Snow {

    @SerializedName("1h")
    private Double lastHour;

    @SerializedName("3h")
    private Double lastThreeHours;

    public Snow(Double lastHour, Double lastThreeHours) {
        this.lastHour = lastHour;
        this.lastThreeHours = lastThreeHours;
    }

    public Double getLastHour() {
        return lastHour;
    }

    public void setLastHour(Double lastHour) {
        this.lastHour = lastHour;
    }

    public Double getLastThreeHours() {
        return lastThreeHours;
    }

    public void setLastThreeHours(Double lastThreeHours) {
        this.lastThreeHours = lastThreeHours;
    }
}
