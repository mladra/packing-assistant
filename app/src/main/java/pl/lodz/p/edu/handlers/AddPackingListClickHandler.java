package pl.lodz.p.edu.handlers;

import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.activities.AddPackingListActivity;
import pl.lodz.p.edu.api.weather.WeatherService;
import pl.lodz.p.edu.api.weather.data.WeatherResponse;
import pl.lodz.p.edu.fragments.ChooseActivitiesPackingListFragment;
import pl.lodz.p.edu.view.model.PackingListCreationParameters;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPackingListClickHandler implements ClickHandler {

    private static final String TAG = "AddPackingListClickHand";

    private FragmentActivity activity;
    private AddPackingListActivity parent;

    public AddPackingListClickHandler(FragmentActivity activity, AddPackingListActivity parent) {
        this.activity = activity;
        this.parent = parent;
    }

    @Override
    public void onClick() {
        final PackingListCreationParameters params = parent.getCreationParameters();
        WeatherService.getInstance(activity).getWeatherByCityName(params.getCityName()).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Log.d(TAG, "Response code: " + response.code());
                Log.d(TAG, "Response: \n" + response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d(TAG, "Failure: " + t.toString());
            }
        });

        final FragmentManager manager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        final ChooseActivitiesPackingListFragment fragment = new ChooseActivitiesPackingListFragment();
        transaction.replace(R.id.fragment_container, fragment);

        transaction.addToBackStack(null);

        transaction.commit();
    }
}
