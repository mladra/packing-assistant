package pl.lodz.p.edu.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import pl.lodz.p.edu.BR;
import pl.lodz.p.edu.R;
import pl.lodz.p.edu.database.PackAssistantDatabase;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import pl.lodz.p.edu.database.entity.definitions.PackingListDefinition;
import pl.lodz.p.edu.database.entity.definitions.SectionDefinition;
import pl.lodz.p.edu.database.entity.instances.ItemInstance;
import pl.lodz.p.edu.database.entity.instances.PackingListInstance;
import pl.lodz.p.edu.database.entity.instances.SectionInstance;
import pl.lodz.p.edu.databinding.FragmentShowPackingListBinding;
import pl.lodz.p.edu.handlers.ClosePackingListHandler;
import pl.lodz.p.edu.handlers.SavePackingListHandler;
import pl.lodz.p.edu.view.model.Item;
import pl.lodz.p.edu.view.model.PackingList;
import pl.lodz.p.edu.view.model.Section;
import pl.lodz.p.edu.view.adapters.SectionViewAdapter;

public class CreatedPackingListFragment extends Fragment {

    private FragmentShowPackingListBinding binding;
    private long packingListInstanceId;
    private PackingList packingList;

    public void setPackingListInstanceId(long packingListInstanceId) {
        this.packingListInstanceId = packingListInstanceId;
    }

    @SuppressLint("SimpleDateFormat")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            super.onCreateView(inflater, container, savedInstanceState);
            this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_packing_list, container, false);
            final RetrievePackingListTask task = new RetrievePackingListTask(PackAssistantDatabase.getInstance(getContext()));
            this.packingList = task.execute(this.packingListInstanceId).get().get(0);

            this.binding.setVariable(BR.instance, this.packingList.getInstance());
            this.binding.sectionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            this.binding.sectionsRecyclerView.setAdapter(new SectionViewAdapter(this.packingList.getSections(), packingList.getInstance().getStatus(), this));

            this.binding.setSaveHandler(new SavePackingListHandler(this));
            this.binding.setCloseHandler(new ClosePackingListHandler(this));
            this.binding.setMaxWeight(calculateWeight(true));
            this.binding.setCurrentWeight(calculateWeight(false));
            this.binding.setDateFormatter(new SimpleDateFormat("dd-MM-yyyy HH:mm"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.binding.getRoot();
    }

    private Double calculateWeight(boolean max) {
        Double sum = 0.0D;
        if (packingList != null) {
            final List<Section> sections = this.packingList.getSections();
            if (sections != null && !sections.isEmpty()) {
                for (Section section : sections) {
                    final List<Item> items = section.getItems();
                    if (items != null && !items.isEmpty()) {
                        for (Item item : items) {
                            if (max) {
                                sum += item.getDefinition().getWeight();
                            } else if (item.getInstance().isSelected()) {
                                sum += item.getDefinition().getWeight();
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }

    public PackingList getPackingList() {
        return packingList;
    }

    public void refreshWeight() {
        final Double weight = calculateWeight(false);
        this.binding.setCurrentWeight(weight);
    }

    private static class RetrievePackingListTask extends AsyncTask<Long, Void, List<PackingList>> {

        private PackAssistantDatabase db;

        RetrievePackingListTask(PackAssistantDatabase db) {
            this.db = db;
        }

        @Override
        protected List<PackingList> doInBackground(Long... ids) {
            final List<PackingList> result = new ArrayList<>();
            for (long id : ids) {
                final PackingListInstance packingListInstance = db.packingListInstancesDao().getById(id);
                final PackingListDefinition packingListDefinition = db.packingListDefinitionsDao().getById(packingListInstance.getPackingListDefinitionId());
                final List<Section> sections = new ArrayList<>();
                final List<SectionInstance> sectionInstances = db.sectionInstancesDao().getByPackingListId(packingListInstance.getId());
                for (SectionInstance sectionInstance : sectionInstances) {
                    final SectionDefinition sectionDefinition = db.sectionDefinitionsDao().getById(sectionInstance.getDefinitionId());
                    final List<ItemInstance> itemInstances = db.itemInstancesDao().getBySectionId(sectionInstance.getId());
                    final List<Item> items = new ArrayList<>();
                    for (ItemInstance itemInstance : itemInstances) {
                        final ItemDefinition itemDefinition = db.itemDefinitionsDao().getById(itemInstance.getDefinitionId());
                        final Item item = new Item(itemDefinition, itemInstance);
                        items.add(item);
                    }
                    final Section section = new Section(sectionDefinition, sectionInstance, items);
                    sections.add(section);
                }
                final PackingList packingList = new PackingList(packingListInstance, packingListDefinition, sections);
                result.add(packingList);
            }
            return result;
        }
    }
}
