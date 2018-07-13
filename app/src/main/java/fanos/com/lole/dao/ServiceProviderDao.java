package fanos.com.lole.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import fanos.com.lole.model.ServiceProvider;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ServiceProviderDao {
    @Insert(onConflict = REPLACE)
    void save(ServiceProvider serviceProvider);
    @Query("SELECT * FROM ServiceProvider WHERE id=:id")
    LiveData<ServiceProvider> load(int id);
}
