package com.fooddelivery.domain.net;

import com.fooddelivery.domain.model.ProductDomainModel;

import java.util.List;

import rx.Observable;

/**
 * Created by taras on 11/11/2017.
 */

public interface IFoodIdentifierFactory {
    Observable<ProductDomainModel> getProductById(String id);

    Observable<List<ProductDomainModel>> getProductListByUserId(String id);
}
