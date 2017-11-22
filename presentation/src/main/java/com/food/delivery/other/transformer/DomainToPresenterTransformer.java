package com.food.delivery.other.transformer;

import com.food.delivery.mvp.model.FoodPackagePresenterModel;
import com.fooddelivery.domain.model.FoodPackageDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taras on 11/19/2017.
 */

public class DomainToPresenterTransformer {

    public List<FoodPackagePresenterModel> transform(List<FoodPackageDomainModel> list) {
        List<FoodPackagePresenterModel> foodPackagePresenterModelList = new ArrayList<>();

        for (FoodPackageDomainModel foodPackageDomainModel : list) {
            FoodPackagePresenterModel storeDomainModel = new FoodPackagePresenterModel();

            storeDomainModel.setLatitude(foodPackageDomainModel.getLatitude());
            storeDomainModel.setLongitude(foodPackageDomainModel.getLongitude());
            storeDomainModel.setStoreId(foodPackageDomainModel.getStoreId());
            storeDomainModel.setStoreName(foodPackageDomainModel.getStoreName());
            storeDomainModel.setPackageInfo(foodPackageDomainModel.getPackageInfo());
            storeDomainModel.setPackagePrice(foodPackageDomainModel.getPackagePrice());
            storeDomainModel.setPackageName(foodPackageDomainModel.getPackageName());

            foodPackagePresenterModelList.add(storeDomainModel);
        }
        return foodPackagePresenterModelList;
    }
}
