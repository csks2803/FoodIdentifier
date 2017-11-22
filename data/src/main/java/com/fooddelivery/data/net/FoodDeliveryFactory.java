package com.fooddelivery.data.net;

import android.content.Context;

import com.fooddelivery.data.utilities.mapper.DataToDomainTransformer;
import com.fooddelivery.domain.model.FoodPackageDomainModel;
import com.fooddelivery.domain.net.IFoodDeliveryFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static com.fooddelivery.data.net.FoodDeliveryRestService.BASE_URL;

/**
 * Created by taras on 11/11/2017.
 */

public class FoodDeliveryFactory implements IFoodDeliveryFactory {

    private final Context mContext;

    @Inject
    public FoodDeliveryFactory(Context context) {
        mContext = context;
    }

    @Override
    public Observable<List<FoodPackageDomainModel>> getStoreDomainModel(double mLatitude, double mLongitude) {
        FoodDeliveryRestApi api = new FoodDeliveryRestApi(BASE_URL);

        DataToDomainTransformer transformer = new DataToDomainTransformer();
        FoodDeliveryFactoryImpl foodDeliveryFactory = new FoodDeliveryFactoryImpl(mContext, api, transformer);

        return foodDeliveryFactory.getStoreList();
    }
}
