package edu.p.lodz.pl.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import edu.p.lodz.pl.database.entity.PackingList;

@Dao
public interface PackingListDao {

    @Query("SELECT * FROM PACKING_LIST_T")
    List<PackingList> getAll();

}
