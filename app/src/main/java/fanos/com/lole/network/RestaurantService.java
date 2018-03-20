package fanos.com.lole.network;

import java.util.List;

import fanos.com.lole.model.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by btinsae on 3/20/2018.
 */

public interface RestaurantService {
    @GET("restaurant")
    Call<List<Restaurant>> getResturants(@Query("lat") double lat, @Query("lng") double lng);
}
