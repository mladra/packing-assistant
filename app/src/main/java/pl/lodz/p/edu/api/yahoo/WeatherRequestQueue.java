package pl.lodz.p.edu.api.yahoo;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class WeatherRequestQueue {

    private static WeatherRequestQueue instance;

    public static WeatherRequestQueue getInstance(Context context) {
        if (instance == null) {
            instance = new WeatherRequestQueue(context);
        }
        return instance;
    }

    private RequestQueue queue;

    private WeatherRequestQueue(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void add(WeatherRequest request) {
        queue.add(request);
    }
}
