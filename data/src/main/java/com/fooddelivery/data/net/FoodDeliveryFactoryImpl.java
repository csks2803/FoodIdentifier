package com.fooddelivery.data.net;

import android.content.Context;

import com.fooddelivery.data.model.ProductEntityModel;
import com.fooddelivery.data.utilities.Utility;
import com.fooddelivery.data.utilities.mapper.DataToDomainTransformer;
import com.fooddelivery.data.utilities.mapper.JsonMapper;
import com.fooddelivery.domain.model.ProductDomainModel;

import java.util.List;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

/**
 * Created by taras on 11/11/2017.
 */

class FoodDeliveryFactoryImpl {

    private final DataToDomainTransformer mTransformer;
    private final FoodDeliveryRestApi mApi;
    private final Context mContext;

    FoodDeliveryFactoryImpl(Context context, FoodDeliveryRestApi api, DataToDomainTransformer transformer) {
        mContext = context;
        mApi = api;
        mTransformer = transformer;
    }

    Observable<ProductDomainModel> getProduct(final String id) {
        return Observable.create(new OnSubscribe<ProductDomainModel>() {
            @Override
            public void call(Subscriber<? super ProductDomainModel> subscriber) {
//                final Call<ProductEntityModel> request = mApi.getService().getProductById(id);
//                request.enqueue(new Callback<List<ProductEntityModel>>() {
//                    @Override
//                    public void onResponse(Call<List<ProductEntityModel>> call, Response<List<ProductEntityModel>> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<ProductEntityModel>> call, Throwable t) {
//
//                    }
//                });

                String json = Utility.loadJSONFromAsset(mContext, "product.json");

                ProductEntityModel listStoreEntity = new JsonMapper().fromJson(json, ProductEntityModel.class);
                ProductDomainModel product = mTransformer.transformProductModel(listStoreEntity);
                subscriber.onNext(product);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<List<ProductDomainModel>> getProductListByUserId(String id) {
        return Observable.create(new OnSubscribe<List<ProductDomainModel>>() {
            @Override
            public void call(Subscriber<? super List<ProductDomainModel>> subscriber) {
//                final Call<List<ProductEntityModel>> request = mApi.getService().getProductById(id);
//                request.enqueue(new Callback<List<ProductEntityModel>>() {
//                    @Override
//                    public void onResponse(Call<List<ProductEntityModel>> call, Response<List<ProductEntityModel>> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<ProductEntityModel>> call, Throwable t) {
//
//                    }
//                });


                String json = Utility.loadJSONFromAsset(mContext, "product-list.json");

                List<ProductEntityModel> listStoreEntity = new JsonMapper().fromJsonArray(json, ProductEntityModel.class);
                List<ProductDomainModel> productList = mTransformer.transformProductModelList(listStoreEntity);
                subscriber.onNext(productList);
                subscriber.onCompleted();
            }
        });
    }
}
