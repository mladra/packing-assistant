package edu.p.lodz.pl.database;

import android.content.Context;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.p.lodz.pl.database.dao.ItemDao;
import edu.p.lodz.pl.database.dao.PackingListDao;
import edu.p.lodz.pl.database.dao.PackingListSectionDao;
import edu.p.lodz.pl.database.dao.SectionDao;
import edu.p.lodz.pl.database.dao.SectionItemDao;
import edu.p.lodz.pl.database.entity.Item;
import edu.p.lodz.pl.database.entity.PackingList;
import edu.p.lodz.pl.database.entity.PackingListSection;
import edu.p.lodz.pl.database.entity.Section;
import edu.p.lodz.pl.database.entity.SectionItem;

@Database(entities = {Item.class, Section.class, PackingList.class, SectionItem.class, PackingListSection.class}, version = 1)
public abstract class PackAssistantDatabase extends RoomDatabase {

    private static final String DB_NAME = "pack-assistant-db";

    private static PackAssistantDatabase INSTANCE;

    public abstract ItemDao itemDao();

    public abstract SectionDao sectionDao();

    public abstract PackingListDao packingListDao();

    public abstract SectionItemDao sectionItemDao();

    public abstract PackingListSectionDao packingListSectionDao();

    public synchronized static PackAssistantDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }

        return INSTANCE;
    }

    private static PackAssistantDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, PackAssistantDatabase.class, DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).itemDao().insertAll(Item.populateData());
                                getInstance(context).packingListDao().insertAll(PackingList.populateData());
                                getInstance(context).sectionDao().insertAll(Section.populateData());
                                getInstance(context).sectionItemDao().insertAll(SectionItem.populateData());
                                getInstance(context).packingListSectionDao().insertAll(PackingListSection.populateData());
                            }
                        });
                    }
                }).build();
    }

}
