package com.fooddelivery.data.utilities.mapper;

import com.fooddelivery.data.model.FoodPackageEntityModel;
import com.fooddelivery.domain.model.FoodPackageDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taras on 11/11/2017.
 */

public class DataToDomainTransformer {

    public List<FoodPackageDomainModel> transformStoreModel(List<FoodPackageEntityModel> storeEntityList) {
        List<FoodPackageDomainModel> storeDomainModelList = new ArrayList<>();

        for (FoodPackageEntityModel store : storeEntityList) {
            FoodPackageDomainModel storeDomainModel = new FoodPackageDomainModel();

            storeDomainModel.setLatitude(store.getLatitude());
            storeDomainModel.setLongitude(store.getLongitude());
            storeDomainModel.setStoreId(store.getStoreId());
            storeDomainModel.setStoreName(store.getStoreName());
            storeDomainModel.setPackageInfo(store.getPackageInfo());
            storeDomainModel.setPackagePrice(store.getPackagePrice());
            storeDomainModel.setPackageName(store.getPackageName());

            storeDomainModelList.add(storeDomainModel);
        }
        return storeDomainModelList;
    }
}
