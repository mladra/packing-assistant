package edu.p.lodz.pl.database;

import android.content.Context;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.p.lodz.pl.database.converters.ActivityEnumTypeConverter;
import edu.p.lodz.pl.database.converters.DateTypeConverter;
import edu.p.lodz.pl.database.converters.StatusEnumTypeConverter;
import edu.p.lodz.pl.database.converters.WeatherEnumTypeConverter;
import edu.p.lodz.pl.database.dao.definitions.ItemDefinitionsDao;
import edu.p.lodz.pl.database.dao.instances.ItemInstancesDao;
import edu.p.lodz.pl.database.dao.definitions.PackingListDefinitionsDao;
import edu.p.lodz.pl.database.dao.instances.PackingListInstancesDao;
import edu.p.lodz.pl.database.dao.definitions.PackingListSectionDefinitionsDao;
import edu.p.lodz.pl.database.dao.definitions.SectionDefinitionsDao;
import edu.p.lodz.pl.database.dao.instances.PackingListSectionInstancesDao;
import edu.p.lodz.pl.database.dao.instances.SectionInstancesDao;
import edu.p.lodz.pl.database.dao.definitions.SectionItemDefinitionsDao;
import edu.p.lodz.pl.database.dao.instances.SectionItemInstancesDao;
import edu.p.lodz.pl.database.entity.definitions.PackingListDefinition;
import edu.p.lodz.pl.database.entity.definitions.SectionDefinition;
import edu.p.lodz.pl.database.entity.definitions.ItemDefinition;
import edu.p.lodz.pl.database.entity.instances.ItemInstance;
import edu.p.lodz.pl.database.entity.instances.PackingListInstance;
import edu.p.lodz.pl.database.entity.definitions.PackingListSectionDefinition;
import edu.p.lodz.pl.database.entity.definitions.SectionItemDefinition;
import edu.p.lodz.pl.database.entity.instances.PackingListSectionInstance;
import edu.p.lodz.pl.database.entity.instances.SectionInstance;
import edu.p.lodz.pl.database.entity.instances.SectionItemInstance;

@Database(entities = {
        ItemDefinition.class,
        SectionDefinition.class,
        PackingListDefinition.class,
        SectionItemDefinition.class,
        PackingListSectionDefinition.class,
        ItemInstance.class,
        SectionInstance.class,
        PackingListInstance.class,
        SectionItemInstance.class,
        PackingListSectionInstance.class}, version = 5)
@TypeConverters({DateTypeConverter.class, ActivityEnumTypeConverter.class, StatusEnumTypeConverter.class, WeatherEnumTypeConverter.class})
public abstract class PackAssistantDatabase extends RoomDatabase {

    private static final String DB_NAME = "pack-assistant-db";

    private static PackAssistantDatabase INSTANCE;

    public abstract ItemDefinitionsDao itemDefinitionsDao();

    public abstract SectionDefinitionsDao sectionDefinitionsDao();

    public abstract PackingListDefinitionsDao packingListDefinitionsDao();

    public abstract SectionItemDefinitionsDao sectionItemDefinitionsDao();

    public abstract PackingListSectionDefinitionsDao packingListSectionDefinitionsDao();

    public abstract PackingListInstancesDao packingListInstancesDao();

    public abstract SectionInstancesDao sectionInstancesDao();

    public abstract ItemInstancesDao itemInstancesDao();

    public abstract PackingListSectionInstancesDao packingListSectionInstancesDao();

    public abstract SectionItemInstancesDao sectionItemInstancesDao();

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
                                getInstance(context).itemDefinitionsDao().insertAll(ItemDefinition.populateData());
                                getInstance(context).sectionDefinitionsDao().insertAll(SectionDefinition.populateData());
                                getInstance(context).packingListDefinitionsDao().insertAll(PackingListDefinition.populateData());
                                getInstance(context).sectionItemDefinitionsDao().insertAll(SectionItemDefinition.populateData());
                                getInstance(context).packingListSectionDefinitionsDao().insertAll(PackingListSectionDefinition.populateData());
                            }
                        });
                    }
                })
                .fallbackToDestructiveMigration()
                .build();
    }

}
