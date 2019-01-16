package edu.p.lodz.pl.database.dao.definitions;

import androidx.room.Dao;
import androidx.room.Insert;
import edu.p.lodz.pl.database.entity.definitions.PackingListSectionDefinition;

@Dao
public interface PackingListSectionDefinitionsDao {

    @Insert
    void insertAll(PackingListSectionDefinition... packingListSectionDefinitions);

}
