package pl.lodz.p.edu.activities;

import android.os.Bundle;

import pl.lodz.p.edu.R;
import pl.lodz.p.edu.databinding.ActivityHomeBinding;
import pl.lodz.p.edu.handlers.ViewTransitionClickHandler;

public class HomeActivity extends AbstractActivity<ActivityHomeBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.initBinding(R.layout.activity_home);

        this.binding.setHandler(new ViewTransitionClickHandler(this, AddPackingListActivity.class));
        this.binding.setItemsHandler(new ViewTransitionClickHandler(this, ItemsActivity.class));
        this.binding.setPackingListsHandler(new ViewTransitionClickHandler(this, PackingListsActivity.class));
        this.binding.setTemplatesHandler(new ViewTransitionClickHandler(this, TemplatesActivity.class));
    }

    //TODO: mladra: [] LIST -> Change created packing list view - 2h
    //TODO: mladra: [] ITEMS -> Change single item view - 2h
    //TODO: mladra: [] ITEMS -> Add possibility to edit item - 4h
    //TODO: mladra: [] TEMPLATES -> Add empty list message - 0.5h
    //TODO: mladra: [X] LIST -> Add required items condition before closing list - IT COULD BE HARD
    //TODO: mladra: [X] ADD LIST -> Integrate with Yahoo Weather and Place API - 8h - IT COULD HARDER
    //TODO: mladra: [X] ITEMS -> Add validation on item addition (weather, activity) - 10min
    //TODO: mladra: [X] LIST -> Change date format for more human - 10min
    //TODO: mladra: [X] ADD PACKING LIST -> Add current weight - 45min
    //TODO: mladra: [X] ITEMS -> Add weight into creation dialog - 15min
    //TODO: mladra: [X] ITEMS -> Change single item layout (show name, weight, activity) - 15min
    //TODO: mladra: [X] ITEMS -> Change screen orientation to portrait only - 2min
    //TODO: mladra: [X] LISTS -> Change single list view and add click handler in order to open edit list activity - 20min
    //TODO: mladra: [X] TEMPLATES -> Add backend logic for template addition - 1h
    //TODO: mladra: [X] ADD LIST -> Add possibility to close list - 20min
    //TODO: mladra: [X] ADD LIST -> Add possibility to save list - 30min
    //TODO: mladra: [X] ADD LIST -> Add possibility to create from template - 1h
    //TODO: mladra: [X] LISTS -> Change status color depending on status - 5min
    //TODO: mladra: [X] ADD_PACKING_LIST -> After choosing activities it was possible to back to choosing activities and create many packing lists - 15min
    //TODO: mladra: [X] LISTS -> Change single packing lists layout (created, destination) - 20min
    //TODO: mladra: [REJECTED] TEMPLATES -> Add possibility to remove template (za dużo zależności, stracilibyśmy istniejące listy)
    //TODO: mladra: [REJECTED] ITEMS -> Add possibility to remove item (za dużo zależności, aby usunąć przedmiot, stracilibyśmy informacje w istniejących listach)
}
