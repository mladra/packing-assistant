package edu.p.lodz.pl.database.entity;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "packing_lists")
public class PackingList {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "created_on")
    private Date creationDate;

    @ColumnInfo(name = "status")
    private StatusEnum status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static PackingList[] populateData() {
        //TODO: mladra: populate data
        return new PackingList[] {};
    }
}
