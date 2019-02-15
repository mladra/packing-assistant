package pl.lodz.p.edu.api.weather.data;

import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    private Double cloudiness;

    public Clouds(Double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public Double getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(Double cloudiness) {
        this.cloudiness = cloudiness;
    }
}
