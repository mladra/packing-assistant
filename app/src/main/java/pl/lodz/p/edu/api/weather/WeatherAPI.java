package pl.lodz.p.edu.api.weather;

import pl.lodz.p.edu.api.weather.data.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("weather")
    Call<WeatherResponse> getWeatherByCityName(@Query("q") String cityName, @Query("appid") String apiKey);

}
