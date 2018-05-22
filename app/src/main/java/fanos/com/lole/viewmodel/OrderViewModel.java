package fanos.com.lole.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import fanos.com.lole.database.AppDatabase;
import fanos.com.lole.model.Order;

public class OrderViewModel extends AndroidViewModel {
    private final static String TAG=OrderViewModel.class.getSimpleName();
    private LiveData<List<Order>> orders;
    public OrderViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "Actively retrieving orders from the database");
        orders= AppDatabase.getInstance(this.getApplication()).orderDao().getAllOrders();
    }

    public LiveData<List<Order>> getOrders() {
        return orders;
    }
}
