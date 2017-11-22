package com.fooddelivery.data.net;

import com.fooddelivery.data.model.FoodPackageEntityModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface which present Otentico API
 */
public interface FoodDeliveryRestService {
    String BASE_URL = "https://db.otenti.co/api/";

    @GET("/api/stores")
    Call<List<FoodPackageEntityModel>> getListStores();
}
