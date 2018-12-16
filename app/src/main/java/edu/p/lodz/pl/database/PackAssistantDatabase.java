package edu.p.lodz.pl.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import edu.p.lodz.pl.database.dao.ItemDao;
import edu.p.lodz.pl.database.dao.PackingListDao;
import edu.p.lodz.pl.database.dao.SectionDao;
import edu.p.lodz.pl.database.entity.Item;
import edu.p.lodz.pl.database.entity.PackingList;
import edu.p.lodz.pl.database.entity.Section;

@Database(entities = {Item.class, Section.class, PackingList.class}, version = 1)
public abstract class PackAssistantDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();
    public abstract SectionDao sectionDao();
    public abstract PackingListDao packingListDao();

}
