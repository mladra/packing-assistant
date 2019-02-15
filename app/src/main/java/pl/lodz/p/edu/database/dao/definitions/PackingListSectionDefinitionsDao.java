package pl.lodz.p.edu.database.dao.definitions;

import androidx.room.Dao;
import androidx.room.Insert;
import pl.lodz.p.edu.database.entity.definitions.PackingListSectionDefinition;

@Dao
public interface PackingListSectionDefinitionsDao {

    @Insert
    void insertAll(PackingListSectionDefinition... packingListSectionDefinitions);

}
