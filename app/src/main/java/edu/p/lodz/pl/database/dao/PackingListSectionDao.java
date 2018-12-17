package edu.p.lodz.pl.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import edu.p.lodz.pl.database.entity.PackingListSection;

@Dao
public interface PackingListSectionDao {

    @Insert
    void insertAll(PackingListSection... packingListSections);

}
