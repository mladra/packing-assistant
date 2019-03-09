package pl.lodz.p.edu.database.dao.definitions;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import pl.lodz.p.edu.database.entity.definitions.SectionItemDefinition;
import io.reactivex.Flowable;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface SectionItemDefinitionsDao {

    @Query("SELECT * FROM section_item_definitions")
    Flowable<List<SectionItemDefinition>> getAll();

    @Insert
    void insertAll(SectionItemDefinition... sectionItemDefinitions);

    @Query("SELECT * FROM section_item_definitions sec_def WHERE sec_def.item_id = :itemId")
    SectionItemDefinition getByItemId(long itemId);

    @Query("SELECT * FROM section_item_definitions sec_def WHERE sec_def.item_id = :itemId AND sec_def.section_id = :sectionId")
    SectionItemDefinition getByItemIdAndSectionId(long itemId, long sectionId);

    @Update(onConflict = REPLACE)
    void update(SectionItemDefinition sectionItemDefinition);
}
