package edu.p.lodz.pl.database.dao.definitions;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.p.lodz.pl.database.entity.definitions.ItemDefinition;
import io.reactivex.Flowable;

@Dao
public interface ItemDefinitionsDao {

    @Query("SELECT * FROM items_definitions")
    Flowable<List<ItemDefinition>> getAll();

    @Query("SELECT i.id, i.name, i.max_temp, i.min_temp, i.weather, i.activity " +
            "FROM items_definitions i " +
            "LEFT JOIN section_item_definitions si ON si.item_id = i.id " +
            "WHERE si.section_id = :id")
    List<ItemDefinition> getBySectionId(Long id);

    @Query("SELECT * FROM items_definitions def WHERE def.id = :id")
    ItemDefinition getById(long id);

    @Insert
    void insertAll(ItemDefinition... itemDefinitions);

}
