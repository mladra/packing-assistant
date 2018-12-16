package edu.p.lodz.pl.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import edu.p.lodz.pl.database.entity.Item;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM ITEM_T")
    List<Item> getAll();

}
