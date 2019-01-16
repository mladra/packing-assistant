package edu.p.lodz.pl.database.dao.instances;

import androidx.room.Dao;
import androidx.room.Insert;
import edu.p.lodz.pl.database.entity.instances.PackingListSectionInstance;

@Dao
public interface PackingListSectionInstancesDao {

    @Insert
    long insertSingle(PackingListSectionInstance packingListSectionInstance);

}
