package edu.p.lodz.pl.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import edu.p.lodz.pl.database.entity.Section;

@Dao
public interface SectionDao {

    @Query("SELECT * FROM SECTION_T")
    List<Section> getAll();

}
