package edu.p.lodz.pl.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.p.lodz.pl.database.entity.Item;
import io.reactivex.Flowable;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM items")
    Flowable<List<Item>> getAll();

    @Insert
    void insertAll(Item... items);

}
