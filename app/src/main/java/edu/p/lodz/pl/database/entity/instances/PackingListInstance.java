package edu.p.lodz.pl.database.entity.instances;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import edu.p.lodz.pl.database.entity.StatusEnum;
import edu.p.lodz.pl.database.entity.definitions.BaseEntity;

@Entity(tableName = "packing_list_instances")
public class PackingListInstance extends BaseEntity {

    @ColumnInfo(name = "packing_list_definition_id")
    private long packingListDefinitionId;

    @ColumnInfo(name = "created_on")
    private Date creationDate;

    @ColumnInfo(name = "status")
    private StatusEnum status;

    public PackingListInstance(long packingListDefinitionId, Date creationDate, StatusEnum status) {
        this.packingListDefinitionId = packingListDefinitionId;
        this.creationDate = creationDate;
        this.status = status;
    }

    public long getPackingListDefinitionId() {
        return packingListDefinitionId;
    }

    public void setPackingListDefinitionId(long packingListDefinitionId) {
        this.packingListDefinitionId = packingListDefinitionId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public static PackingListInstance[] populateData() {
        //TODO: mladra: populate data
        return new PackingListInstance[]{};
    }
}
