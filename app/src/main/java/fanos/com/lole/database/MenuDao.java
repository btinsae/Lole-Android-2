package fanos.com.lole.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fanos.com.lole.model.ServiceProviderMenu;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface MenuDao {
    @Query("SELECT * FROM ServiceProviderMenu")
    LiveData<List<ServiceProviderMenu>> loadAllMenu();

    @Query("SELECT * FROM ServiceProviderMenu WHERE updated_at=:fresh_timeout")
    boolean hasMenu(long fresh_timeout);

    @Insert(onConflict = REPLACE)
    void save(ServiceProviderMenu menu);
}
