package com.fooddelivery.domain.net;

import com.fooddelivery.domain.model.FoodPackageDomainModel;

import java.util.List;

import rx.Observable;

/**
 * Created by taras on 11/11/2017.
 */

public interface IFoodDeliveryFactory {
    Observable<List<FoodPackageDomainModel>> getStoreDomainModel(double mLatitude, double mLongitude);
}
