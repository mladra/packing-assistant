package edu.p.lodz.pl.database.dao.definitions;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.p.lodz.pl.database.entity.definitions.PackingListDefinition;
import edu.p.lodz.pl.database.entity.instances.PackingListInstance;

@Dao
public interface PackingListDefinitionsDao {

    @Query("SELECT * FROM packing_list_definitions")
    List<PackingListDefinition> getAll();

    @Query("SELECT * FROM packing_list_definitions def WHERE def.id = :id")
    PackingListDefinition getById(long id);

    @Insert
    long insertSingle(PackingListDefinition packingList);

    @Insert
    long[] insertAll(PackingListDefinition... packingLists);

}
