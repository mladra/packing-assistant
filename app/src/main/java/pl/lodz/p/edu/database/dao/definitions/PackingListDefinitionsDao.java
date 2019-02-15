package pl.lodz.p.edu.database.dao.definitions;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Flowable;
import pl.lodz.p.edu.database.entity.definitions.PackingListDefinition;

@Dao
public interface PackingListDefinitionsDao {

    @Query("SELECT * FROM packing_list_definitions")
    List<PackingListDefinition> getAll();

    @Query("SELECT * FROM packing_list_definitions def WHERE def.is_template = :isTemplate")
    Flowable<List<PackingListDefinition>> getAll(boolean isTemplate);

    @Query("SELECT * FROM packing_list_definitions def WHERE def.id = :id")
    PackingListDefinition getById(long id);

    @Insert
    long insertSingle(PackingListDefinition packingList);

    @Insert
    long[] insertAll(PackingListDefinition... packingLists);

}
