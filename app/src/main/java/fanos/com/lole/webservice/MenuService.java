package fanos.com.lole.webservice;

import java.util.List;

import fanos.com.lole.model.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MenuService {
    @GET("service-provider/{service_provider_id}/menu")
    Call<List<Item>> getMenu(@Path("service_provider_id") int id);
}
