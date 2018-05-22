package fanos.com.lole.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fanos.com.lole.model.Order;

@Dao
public interface OrderDao {
    @Query("SELECT * FROM orders")
    LiveData<List<Order>> getAllOrders();

    @Insert
    void insertOrder(Order order);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateOrder(Order order);

    @Delete
    void deleteOrder(Order order);

    @Query("SELECT * FROM orders WHERE id=:id")
    LiveData<Order> loadOrderById(int id);
}
