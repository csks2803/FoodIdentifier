package com.fooddelivery.data.net;

import android.content.Context;

import com.fooddelivery.data.utilities.mapper.DataToDomainTransformer;
import com.fooddelivery.domain.model.ProductDomainModel;
import com.fooddelivery.domain.net.IFoodIdentifierFactory;

import javax.inject.Inject;

import rx.Observable;

import static com.fooddelivery.data.net.FoodDeliveryRestService.BASE_URL;

/**
 * Created by taras on 11/11/2017.
 */

public class FoodIdentifierFactory implements IFoodIdentifierFactory {

    private final Context mContext;

    @Inject
    public FoodIdentifierFactory(Context context) {
        mContext = context;
    }

    @Override
    public Observable<ProductDomainModel> getProductById(String id) {
        FoodDeliveryRestApi api = new FoodDeliveryRestApi(BASE_URL);

        DataToDomainTransformer transformer = new DataToDomainTransformer();
        FoodDeliveryFactoryImpl foodDeliveryFactory = new FoodDeliveryFactoryImpl(mContext, api, transformer);

        return foodDeliveryFactory.getProduct(id);
    }
}
