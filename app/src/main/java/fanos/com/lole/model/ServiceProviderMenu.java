package fanos.com.lole.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class ServiceProviderMenu {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int serviceProviderId;
    @ColumnInfo(name = "created_at")
    private Date createdAt;
    @ColumnInfo(name = "updated_at")
    private Date updatedAt;
}
