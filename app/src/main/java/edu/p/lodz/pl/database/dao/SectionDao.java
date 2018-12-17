package edu.p.lodz.pl.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.p.lodz.pl.database.entity.Section;

@Dao
public interface SectionDao {

    @Query("SELECT * FROM sections")
    List<Section> getAll();

    @Insert
    void insertAll(Section... sections);

}
