package edu.p.lodz.pl.database.dao.instances;

import androidx.room.Dao;
import androidx.room.Insert;
import edu.p.lodz.pl.database.entity.instances.SectionItemInstance;

@Dao
public interface SectionItemInstancesDao {

    @Insert
    long insertSingle(SectionItemInstance sectionItemInstance);

}
