package pl.lodz.p.edu.database.dao.instances;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import pl.lodz.p.edu.database.entity.instances.ItemInstance;

@Dao
public interface ItemInstancesDao {

    @Query("SELECT i.id, i.definition_id, i.is_selected " +
            "FROM item_instances i " +
            "LEFT JOIN section_item_instances si ON si.item_id = i.id " +
            "WHERE si.section_id = :id")
    List<ItemInstance> getBySectionId(Long id);

    @Insert
    long insertSingle(ItemInstance itemInstance);

}
