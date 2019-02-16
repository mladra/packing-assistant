package pl.lodz.p.edu.activities;

import android.os.Bundle;

import pl.lodz.p.edu.R;
import pl.lodz.p.edu.api.weather.WeatherService;
import pl.lodz.p.edu.databinding.ActivityHomeBinding;
import pl.lodz.p.edu.handlers.ViewTransitionClickHandler;

public class HomeActivity extends AbstractActivity<ActivityHomeBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.initBinding(R.layout.activity_home);
        setHeaderTitle(R.string.home_title);

        this.binding.setHandler(new ViewTransitionClickHandler(this, AddPackingListActivity.class));
        this.binding.setItemsHandler(new ViewTransitionClickHandler(this, ItemsActivity.class));
        this.binding.setPackingListsHandler(new ViewTransitionClickHandler(this, PackingListsActivity.class));
        this.binding.setTemplatesHandler(new ViewTransitionClickHandler(this, TemplatesActivity.class));
    }

    //TODO: mladra: [X] LISTS -> Change single list view and add click handler in order to open edit list activity - 20min
    //TODO: mladra: [X] ITEMS -> Change single item layout (show name, weight, activity) - 15min
    //TODO: mladra: [] ITEMS -> Add weight into creation dialog - 1h
    //TODO: mladra: [] ADD PACKING LIST -> Add current weight - 1h
    //TODO: mladra: [X] ITEMS -> Change screen orientation to portrait only - 2min
    //TODO: mladra: [REJECTED] ITEMS -> Add possibility to remove item - 1h (za dużo zależności, aby usunąć przedmiot)
    //TODO: mladra: [] ITEMS -> Add possibility to edit item - 4h
    //TODO: mladra: [] ITEMS -> Add validation on item addition (weather, activity) - 1.5h
    //TODO: mladra: [] TEMPLATES -> Add empty list message - 0.5h
    //TODO: mladra: [] TEMPLATES -> Add backend logic for template addition - 2h
    //TODO: mladra: [] TEMPLATES -> Add possibility to remove template - 1h
    //TODO: mladra: [] ADD LIST -> Integrate with Yahoo Weather and Place API - 8h
    //TODO: mladra: [X] ADD LIST -> Add possibility to close list - 20min
    //TODO: mladra: [X] ADD LIST -> Add possibility to save list - 30min
    //TODO: mladra: [] ADD LIST -> Add possibility to create from template - 1h
    //TODO: mladra: [X] LISTS -> Change status color depending on status - 5min
    //TODO: mladra: [X] ADD_PACKING_LIST -> After choosing activities it was possible to back to choosing activities and create many packing lists
    //TODO: mladra: [] LISTS -> Change single packing lists layout (created, destination)
}
