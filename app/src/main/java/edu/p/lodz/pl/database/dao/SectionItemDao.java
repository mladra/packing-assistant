package edu.p.lodz.pl.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import edu.p.lodz.pl.database.entity.SectionItem;

@Dao
public interface SectionItemDao {

    @Insert
    void insertAll(SectionItem... sectionItems);

}
