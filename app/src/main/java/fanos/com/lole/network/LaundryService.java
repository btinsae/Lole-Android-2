package fanos.com.lole.network;

import java.util.List;

import fanos.com.lole.model.Laundry;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by btinsae on 3/24/2018.
 */

public interface LaundryService {
    @GET
    Call<List<Laundry>> getLaundry(@Query("lat") double lat,@Query("lng") double lng);
}
