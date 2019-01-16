package edu.p.lodz.pl.database.dao.definitions;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.p.lodz.pl.database.entity.definitions.SectionItemDefinition;
import io.reactivex.Flowable;

@Dao
public interface SectionItemDefinitionsDao {

    @Query("SELECT * FROM section_item_definitions")
    Flowable<List<SectionItemDefinition>> getAll();

    @Insert
    void insertAll(SectionItemDefinition... sectionItemDefinitions);

}
