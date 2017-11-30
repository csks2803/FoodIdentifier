package com.fooddelivery.data.net;

import com.fooddelivery.data.model.ProductEntityModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Interface which present Otentico API
 */
public interface FoodDeliveryRestService {
    String BASE_URL = "https://db.otenti.co/api/";

    @POST("/api/stores")
    Call<ProductEntityModel> getProductById(String id);

    @GET("/api/getProductListt")
    Call<ProductEntityModel> getProductListByUserId(String id);
}
