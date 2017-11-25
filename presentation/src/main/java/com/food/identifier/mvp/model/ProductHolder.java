package com.food.identifier.mvp.model;

import javax.inject.Inject;

/**
 * Created by taras on 11/25/2017.
 */

public class ProductHolder {

    @Inject
    public ProductHolder() {
    }

    private ProductPresentationModel mProductHolder;

    public void setProductHolder(ProductPresentationModel productHolder) {
        mProductHolder = productHolder;
    }

    public ProductPresentationModel getProductHolder() {
        return mProductHolder;
    }

}
