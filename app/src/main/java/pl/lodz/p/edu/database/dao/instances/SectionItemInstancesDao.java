package pl.lodz.p.edu.database.dao.instances;

import androidx.room.Dao;
import androidx.room.Insert;
import pl.lodz.p.edu.database.entity.instances.SectionItemInstance;

@Dao
public interface SectionItemInstancesDao {

    @Insert
    long insertSingle(SectionItemInstance sectionItemInstance);

}
