package com.fooddelivery.data.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.fooddelivery.data.BuildConfig.DEBUG;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Taras Matolinets.
 * Date : 9/7/2017.
 * Time : 9:50 AM.
 * Company : FoodDelivery
 */
public class FoodDeliveryRestApi {
    private static final int TIMEOUT = 7;
    private final String baseUrl;
    private final FoodDeliveryRestService service;

    public FoodDeliveryRestApi(String baseUrl) {
        this.baseUrl = baseUrl;
        Retrofit retrofit = buildFoodDeliveryApi();

        service = retrofit.create(FoodDeliveryRestService.class);
    }

    private Retrofit buildFoodDeliveryApi() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (DEBUG) {
            builder.addInterceptor(buildLoggingInterceptor());
        }
        builder.connectTimeout(TIMEOUT, SECONDS);
        OkHttpClient httpClient = builder.build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public FoodDeliveryRestService getService() {
        return service;
    }

    private HttpLoggingInterceptor buildLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
