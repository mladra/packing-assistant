package pl.lodz.p.edu.database.dao.instances;

import androidx.room.Dao;
import androidx.room.Insert;
import pl.lodz.p.edu.database.entity.instances.PackingListSectionInstance;

@Dao
public interface PackingListSectionInstancesDao {

    @Insert
    long insertSingle(PackingListSectionInstance packingListSectionInstance);

}
