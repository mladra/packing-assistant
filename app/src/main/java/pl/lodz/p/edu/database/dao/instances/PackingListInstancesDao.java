package pl.lodz.p.edu.database.dao.instances;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import pl.lodz.p.edu.database.entity.instances.PackingListInstance;
import io.reactivex.Flowable;
import pl.lodz.p.edu.view.model.PackingList;

@Dao
public interface PackingListInstancesDao {

    @Insert
    long insertSingle(PackingListInstance packingList);

    @Query("SELECT ins.id, ins.created_on, ins.packing_list_definition_id, ins.status, ins.destination " +
            "FROM packing_list_instances ins " +
            "WHERE ins.id = :packingListInstanceId")
    PackingListInstance getById(long packingListInstanceId);

    @Query("SELECT * FROM packing_list_instances")
    Flowable<List<PackingListInstance>> getAll();

    @Update
    void update(PackingListInstance... packingLists);
}
