package edu.p.lodz.pl.database.dao.definitions;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.p.lodz.pl.database.entity.definitions.SectionDefinition;
import io.reactivex.Flowable;

@Dao
public interface SectionDefinitionsDao {

    @Query("SELECT * FROM section_definitions")
    Flowable<List<SectionDefinition>> getAll();

    @Query("SELECT * FROM section_definitions def WHERE def.name IN (:names)")
    List<SectionDefinition> getByName(String[] names);

    @Query("SELECT * FROM section_definitions def WHERE def.id = :id")
    SectionDefinition getById(long id);

    @Insert
    void insertAll(SectionDefinition... sectionDefinitions);

}
