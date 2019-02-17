package pl.lodz.p.edu.database.dao.instances;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import pl.lodz.p.edu.database.entity.instances.SectionItemInstance;

@Dao
public interface SectionItemInstancesDao {

    @Insert
    long insertSingle(SectionItemInstance sectionItemInstance);

    @Query("SELECT sii.item_id, sii.section_id, sii.is_required " +
            "FROM section_item_instances sii " +
            "WHERE sii.item_id = :itemId AND sii.section_id = :sectionId")
    SectionItemInstance byItemIdAndSectionId(Long itemId, Long sectionId);

}
