package pl.lodz.p.edu.database.entity.definitions;

import androidx.room.PrimaryKey;

public abstract class BaseEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
