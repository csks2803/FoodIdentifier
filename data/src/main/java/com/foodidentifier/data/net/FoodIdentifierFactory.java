package com.foodidentifier.data.net;

import android.content.Context;

import com.foodidentifier.data.utilities.mapper.DataToDomainTransformer;
import com.foodidentifier.domain.model.ProductDomainModel;
import com.foodidentifier.domain.model.RegisterFormDomainModel;
import com.foodidentifier.domain.model.UserDomainModel;
import com.foodidentifier.domain.net.IFoodIdentifierFactory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static com.foodidentifier.data.net.FoodDeliveryRestService.BASE_URL;

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

    @Override
    public Observable<List<ProductDomainModel>> getProductListByUserId(String id) {
        FoodDeliveryRestApi api = new FoodDeliveryRestApi(BASE_URL);

        DataToDomainTransformer transformer = new DataToDomainTransformer();
        FoodDeliveryFactoryImpl foodDeliveryFactory = new FoodDeliveryFactoryImpl(mContext, api, transformer);

        return foodDeliveryFactory.getProductListByUserId(id);
    }

    @Override
    public Observable<UserDomainModel> loginUser(final String login, final String password) {
        FoodDeliveryRestApi api = new FoodDeliveryRestApi(BASE_URL);

        DataToDomainTransformer transformer = new DataToDomainTransformer();
        FoodDeliveryFactoryImpl foodDeliveryFactory = new FoodDeliveryFactoryImpl(mContext, api, transformer);

        return foodDeliveryFactory.loginUser(login, password);
    }

    @Override
    public Observable<UserDomainModel> registerUser(RegisterFormDomainModel registerFormDomainModel) {
        FoodDeliveryRestApi api = new FoodDeliveryRestApi(BASE_URL);

        DataToDomainTransformer transformer = new DataToDomainTransformer();
        FoodDeliveryFactoryImpl foodDeliveryFactory = new FoodDeliveryFactoryImpl(mContext, api, transformer);

        return foodDeliveryFactory.registerUser(registerFormDomainModel);
    }
}
