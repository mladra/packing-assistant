package pl.lodz.p.edu.database.entity.instances;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "section_instances")
public class SectionInstance extends AbstractInstance {

    @Ignore
    public SectionInstance(long definitionId, boolean selected) {
        super(definitionId, selected);
    }

    public SectionInstance(long definitionId) {
        super(definitionId, false);
    }
}
