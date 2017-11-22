package com.fooddelivery.data.net;

import android.content.Context;

import com.fooddelivery.data.model.FoodPackageEntityModel;
import com.fooddelivery.data.utilities.Utility;
import com.fooddelivery.data.utilities.mapper.DataToDomainTransformer;
import com.fooddelivery.data.utilities.mapper.JsonMapper;
import com.fooddelivery.domain.model.FoodPackageDomainModel;

import java.util.List;

import rx.Observable;
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

    Observable<List<FoodPackageDomainModel>> getStoreList() {
        return Observable.create(new Observable.OnSubscribe<List<FoodPackageDomainModel>>() {
            @Override
            public void call(Subscriber<? super List<FoodPackageDomainModel>> subscriber) {
//                final Call<List<FoodPackageEntityModel>> request = mApi.getService().getListStores();
//                request.enqueue(new Callback<List<FoodPackageEntityModel>>() {
//                    @Override
//                    public void onResponse(Call<List<FoodPackageEntityModel>> call, Response<List<FoodPackageEntityModel>> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<FoodPackageEntityModel>> call, Throwable t) {
//
//                    }
//                });

                String json = Utility.loadJSONFromAsset(mContext, "storelist.json");

                List<FoodPackageEntityModel> listStoreEntity = new JsonMapper().fromJsonArray(json, FoodPackageEntityModel.class);
                List<FoodPackageDomainModel> storeDomainModelList = mTransformer.transformStoreModel(listStoreEntity);
                subscriber.onNext(storeDomainModelList);
            }
        });
    }

}
