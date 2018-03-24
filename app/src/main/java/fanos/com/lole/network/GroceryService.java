package fanos.com.lole.network;

import java.util.List;

import fanos.com.lole.model.Drink;
import fanos.com.lole.model.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by btinsae on 3/24/2018.
 */

public interface GroceryService {
    @GET
    Call<List<Drink>> getGrocery(@Query("lat") double lat, @Query("lng") double lng);
}
