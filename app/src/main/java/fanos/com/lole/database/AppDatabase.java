package fanos.com.lole.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import fanos.com.lole.model.Order;
import fanos.com.lole.model.ServiceProvider;
import fanos.com.lole.model.ServiceProviderMenu;

@Database(entities = {Order.class, ServiceProvider.class, ServiceProviderMenu.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static AppDatabase sInstance;
    private static final String DATABASE_NAME = "lole-db";
    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (sInstance == null) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext()
                        , AppDatabase.class, DATABASE_NAME).build();
            }
        }
        Log.d(LOG_TAG, "Getting database instance");
        return sInstance;
    }

    public abstract OrderDao orderDao();
}
