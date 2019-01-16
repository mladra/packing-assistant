package edu.p.lodz.pl.database.entity.instances;

import androidx.room.ColumnInfo;
import edu.p.lodz.pl.database.entity.definitions.BaseEntity;

public abstract class AbstractInstance extends BaseEntity {

    @ColumnInfo(name = "definition_id")
    protected long definitionId;

    @ColumnInfo(name = "is_selected")
    protected boolean selected;

    public AbstractInstance(long definitionId, boolean selected) {
        this.definitionId = definitionId;
        this.selected = selected;
    }

    public long getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(long definitionId) {
        this.definitionId = definitionId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
