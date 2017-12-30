package com.foodidentifier.data.net;

import android.content.Context;

import com.foodidentifier.data.exceptions.NotValidCredentialException;
import com.foodidentifier.data.model.ProductEntityModel;
import com.foodidentifier.data.model.RegisterFormEntityModel;
import com.foodidentifier.data.model.UserEntityModel;
import com.foodidentifier.data.utilities.Utility;
import com.foodidentifier.data.utilities.mapper.DataToDomainTransformer;
import com.foodidentifier.data.utilities.mapper.DomainToDataTransformer;
import com.foodidentifier.data.utilities.mapper.JsonMapper;
import com.foodidentifier.domain.model.ProductDomainModel;
import com.foodidentifier.domain.model.RegisterFormDomainModel;
import com.foodidentifier.domain.model.UserDomainModel;

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

    Observable<List<ProductDomainModel>> getProductListByUserId(String id) {
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

    Observable<UserDomainModel> loginUser(String login, final String password) {
        return Observable.create(new OnSubscribe<UserDomainModel>() {
            @Override
            public void call(Subscriber<? super UserDomainModel> subscriber) {

//                                final Call<List<ProductEntityModel>> request = mApi.getService().getUserById(login,password);
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

                if (password.equalsIgnoreCase("123456aA!")) {

                    String json = Utility.loadJSONFromAsset(mContext, "user_customer.json");
                    UserEntityModel userEntity = new JsonMapper().fromJson(json, UserEntityModel.class);

                    UserDomainModel userDomainModel = mTransformer.transformUserModel(userEntity);

                    subscriber.onNext(userDomainModel);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new NotValidCredentialException("Wrong Password"));
                }
            }
        });
    }

    Observable<UserDomainModel> registerUser(final RegisterFormDomainModel registerUser) {
        return Observable.create(new OnSubscribe<UserDomainModel>() {
            @Override
            public void call(Subscriber<? super UserDomainModel> subscriber) {
                DomainToDataTransformer domainToDataTransformer = new DomainToDataTransformer();
                RegisterFormEntityModel registerFormEntityModel = domainToDataTransformer.transformRegister(registerUser);
//                final Call<Void> request = mApi.getService().registerUser(registerFormEntityModel);
//                request.enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//
//                    }
//                });

                if (registerUser.getLogin().equalsIgnoreCase("test.user@gmail.com") && registerUser.getType() == 0) {

                    String json = Utility.loadJSONFromAsset(mContext, "user_customer.json");
                    UserEntityModel userEntity = new JsonMapper().fromJson(json, UserEntityModel.class);

                    UserDomainModel userDomainModel = mTransformer.transformUserModel(userEntity);
                    subscriber.onNext(userDomainModel);

                    subscriber.onCompleted();
                } else if (registerUser.getLogin().equalsIgnoreCase("test.organization@gmail.com") && registerUser.getType() == 1) {
                    String json = Utility.loadJSONFromAsset(mContext, "user_organization.json");
                    UserEntityModel userEntity = new JsonMapper().fromJson(json, UserEntityModel.class);

                    UserDomainModel userDomainModel = mTransformer.transformUserModel(userEntity);
                    subscriber.onNext(userDomainModel);

                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new NotValidCredentialException("This organization was not register. Please contact us for more details."));
                }
            }
        });
    }
}
