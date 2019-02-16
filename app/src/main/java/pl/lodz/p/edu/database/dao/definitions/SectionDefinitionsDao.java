package pl.lodz.p.edu.database.dao.definitions;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import pl.lodz.p.edu.database.entity.definitions.SectionDefinition;
import io.reactivex.Flowable;

@Dao
public interface SectionDefinitionsDao {

    @Query("SELECT * FROM section_definitions")
    Flowable<List<SectionDefinition>> getAll();

    @Query("SELECT * FROM section_definitions def WHERE def.name IN (:names)")
    List<SectionDefinition> getByName(String... names);

    @Query("SELECT * FROM section_definitions def WHERE def.id = :id")
    SectionDefinition getById(long id);

    @Query("SELECT def.id, def.name, def.required " +
            "FROM section_definitions def " +
            "LEFT JOIN packing_list_section_definitions join_def ON def.id = join_def.section_id " +
            "WHERE join_def.packing_list_id = :id")
    List<SectionDefinition> getByPackingListId(long id);

    @Insert
    void insertAll(SectionDefinition... sectionDefinitions);

    @Insert
    long insertSingle(SectionDefinition sectionDefinition);
}
