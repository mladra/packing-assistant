package edu.p.lodz.pl.database.entity.instances;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "item_instances")
public class ItemInstance extends AbstractInstance {

    @Ignore
    public ItemInstance(long definitionId, boolean selected) {
        super(definitionId, selected);
    }

    public ItemInstance(long definitionId) {
        super(definitionId, false);
    }
}
