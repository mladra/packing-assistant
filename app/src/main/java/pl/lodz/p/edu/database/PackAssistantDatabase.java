package pl.lodz.p.edu.database;

import android.content.Context;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import pl.lodz.p.edu.database.converters.ActivityEnumTypeConverter;
import pl.lodz.p.edu.database.converters.DateTypeConverter;
import pl.lodz.p.edu.database.converters.StatusEnumTypeConverter;
import pl.lodz.p.edu.database.converters.WeatherEnumTypeConverter;
import pl.lodz.p.edu.database.dao.definitions.ItemDefinitionsDao;
import pl.lodz.p.edu.database.dao.instances.ItemInstancesDao;
import pl.lodz.p.edu.database.dao.definitions.PackingListDefinitionsDao;
import pl.lodz.p.edu.database.dao.instances.PackingListInstancesDao;
import pl.lodz.p.edu.database.dao.definitions.PackingListSectionDefinitionsDao;
import pl.lodz.p.edu.database.dao.definitions.SectionDefinitionsDao;
import pl.lodz.p.edu.database.dao.instances.PackingListSectionInstancesDao;
import pl.lodz.p.edu.database.dao.instances.SectionInstancesDao;
import pl.lodz.p.edu.database.dao.definitions.SectionItemDefinitionsDao;
import pl.lodz.p.edu.database.dao.instances.SectionItemInstancesDao;
import pl.lodz.p.edu.database.entity.definitions.PackingListDefinition;
import pl.lodz.p.edu.database.entity.definitions.SectionDefinition;
import pl.lodz.p.edu.database.entity.definitions.ItemDefinition;
import pl.lodz.p.edu.database.entity.instances.ItemInstance;
import pl.lodz.p.edu.database.entity.instances.PackingListInstance;
import pl.lodz.p.edu.database.entity.definitions.PackingListSectionDefinition;
import pl.lodz.p.edu.database.entity.definitions.SectionItemDefinition;
import pl.lodz.p.edu.database.entity.instances.PackingListSectionInstance;
import pl.lodz.p.edu.database.entity.instances.SectionInstance;
import pl.lodz.p.edu.database.entity.instances.SectionItemInstance;

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
        PackingListSectionInstance.class}, version = 8, exportSchema = false)
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
