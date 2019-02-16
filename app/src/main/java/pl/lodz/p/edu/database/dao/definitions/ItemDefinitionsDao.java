package pl.lodz.p.edu.database.dao.definitions;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import io.reactivex.Flowable;

@Dao
public interface ItemDefinitionsDao {

    @Query("SELECT * FROM items_definitions")
    Flowable<List<ItemDefinition>> getAll();

    @Query("SELECT i.id, i.name, i.max_temp, i.min_temp, i.weather, i.activity, i.weight " +
            "FROM items_definitions i " +
            "LEFT JOIN section_item_definitions si ON si.item_id = i.id " +
            "WHERE si.section_id = :id")
    List<ItemDefinition> getBySectionId(Long id);

    @Query("SELECT * FROM items_definitions def WHERE def.id = :id")
    ItemDefinition getById(long id);

    @Insert
    long[] insertAll(ItemDefinition... itemDefinitions);

    @Query("SELECT i.id, i.name, i.max_temp, i.min_temp, i.weather, i.activity " +
            "FROM items_definitions i " +
            "WHERE i.name = :name")
    ItemDefinition getByName(String name);

    @Delete
    void delete(ItemDefinition... itemDefinition);

}
