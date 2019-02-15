package pl.lodz.p.edu.database.dao.instances;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import pl.lodz.p.edu.database.entity.instances.SectionInstance;

@Dao
public interface SectionInstancesDao {

    @Query("SELECT si.id, si.definition_id, si.is_selected FROM section_instances si " +
            "LEFT JOIN packing_list_section_instances psi ON psi.section_id = si.id " +
            "WHERE psi.packing_list_id = :packingListId")
    List<SectionInstance> getByPackingListId(long packingListId);

    @Insert
    long insertSingle(SectionInstance sectionInstance);

}
