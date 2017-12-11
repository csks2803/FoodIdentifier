package com.food.identifier.other.transformer;

import com.food.identifier.mvp.model.RegisterFormPresenterModel;
import com.foodidentifier.domain.model.RegisterFormDomainModel;

/**
 * Created by taras on 11/11/2017.
 */

public class PresenterToDataTransformer {

    public RegisterFormDomainModel transformRegistrationForm(RegisterFormPresenterModel registerPresenterModel) {
        RegisterFormDomainModel registerFormDomainModel = new RegisterFormDomainModel();

        registerFormDomainModel.setFirstName(registerPresenterModel.getLastName());
        registerFormDomainModel.setLastName(registerPresenterModel.getLastName());
        registerFormDomainModel.setLogin(registerPresenterModel.getLogin());
        registerFormDomainModel.setPassword(registerPresenterModel.getPassword());
        registerFormDomainModel.setType(registerPresenterModel.getType());

        return registerFormDomainModel;
    }
}
