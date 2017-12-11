package com.foodidentifier.domain.net;

import com.foodidentifier.domain.model.ProductDomainModel;
import com.foodidentifier.domain.model.RegisterFormDomainModel;
import com.foodidentifier.domain.model.UserDomainModel;

import java.util.List;

import rx.Observable;

/**
 * Created by taras on 11/11/2017.
 */

public interface IFoodIdentifierFactory {
    Observable<ProductDomainModel> getProductById(String id);

    Observable<List<ProductDomainModel>> getProductListByUserId(String id);

    Observable<UserDomainModel> loginUser(String password, String login);

    Observable<Void> registerUser(RegisterFormDomainModel registerFormDomainModel);
}
