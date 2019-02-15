package pl.lodz.p.edu.api.weather;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import pl.lodz.p.edu.api.weather.data.WeatherResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherService {

    private static final String TAG = "WeatherService";

    private static WeatherService INSTANCE;

    public static WeatherService getInstance(Activity activity) {
        if (INSTANCE == null) {
            INSTANCE = new WeatherService(activity);
        }
        return INSTANCE;
    }

    private WeatherAPI weatherAPI;
    private String apiKey;

    private WeatherService(Activity activity) {
        try {
            final ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
            final Bundle bundle = ai.metaData;
            this.apiKey = bundle.getString("weather_api_key");
            Log.d(TAG, "Weather API key: " + apiKey);

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            this.weatherAPI = retrofit.create(WeatherAPI.class);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Call<WeatherResponse> getWeatherByCityName(final String cityName) {
        return this.weatherAPI.getWeatherByCityName(cityName, apiKey);
    }
}
