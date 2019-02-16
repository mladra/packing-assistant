package pl.lodz.p.edu.database.entity.instances;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import pl.lodz.p.edu.database.entity.StatusEnum;
import pl.lodz.p.edu.database.entity.definitions.BaseEntity;

@Entity(tableName = "packing_list_instances")
public class PackingListInstance extends BaseEntity {

    @ColumnInfo(name = "packing_list_definition_id")
    private long packingListDefinitionId;

    @ColumnInfo(name = "created_on")
    private Date creationDate;

    @ColumnInfo(name = "status")
    private StatusEnum status;

    @ColumnInfo(name = "destination")
    private String destination;

    public PackingListInstance(long packingListDefinitionId, Date creationDate, StatusEnum status, String destination) {
        this.packingListDefinitionId = packingListDefinitionId;
        this.creationDate = creationDate;
        this.status = status;
        this.destination = destination;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
