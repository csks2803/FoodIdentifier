package com.foodidentifier.data.utilities.mapper;

import com.foodidentifier.data.model.RegisterFormEntityModel;
import com.foodidentifier.domain.model.RegisterFormDomainModel;

/**
 * Created by taras on 11/11/2017.
 */

public class DomainToDataTransformer {

    public RegisterFormEntityModel transformRegister(RegisterFormDomainModel registerFormDomainModel) {
        RegisterFormEntityModel registerFormEntityModel = new RegisterFormEntityModel();

        registerFormEntityModel.setFirstName(registerFormDomainModel.getLastName());
        registerFormEntityModel.setLastName(registerFormDomainModel.getLastName());
        registerFormEntityModel.setLogin(registerFormDomainModel.getLogin());
        registerFormEntityModel.setPassword(registerFormDomainModel.getPassword());
        registerFormEntityModel.setType(registerFormDomainModel.getType());

        return registerFormEntityModel;
    }
}
