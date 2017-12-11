package com.foodidentifier.data.net;

import com.foodidentifier.data.model.ProductEntityModel;
import com.foodidentifier.data.model.RegisterFormEntityModel;
import com.foodidentifier.data.model.UserEntityModel;

import retrofit2.Call;
import retrofit2.http.Body;
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

    @POST("/api/getUser")
    Call<UserEntityModel> getUserById(String login, String password);

    @POST("/api/register")
    Call<Void> registerUser(@Body RegisterFormEntityModel registerFormEntityModel);
}
