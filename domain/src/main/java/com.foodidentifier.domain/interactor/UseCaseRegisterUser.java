package com.foodidentifier.domain.interactor;

import com.foodidentifier.domain.executor.PostExecutionThread;
import com.foodidentifier.domain.executor.ThreadExecutor;
import com.foodidentifier.domain.model.RegisterFormDomainModel;
import com.foodidentifier.domain.net.IFoodIdentifierFactory;

import rx.Observable;

/**
 * Created by taras on 11/11/2017.
 */

public class UseCaseRegisterUser extends UseCase {

    private final IFoodIdentifierFactory mFactory;
    private final RegisterFormDomainModel mRegisterModel;

    public UseCaseRegisterUser(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IFoodIdentifierFactory factory, RegisterFormDomainModel registerFormDomainModel) {
        super(threadExecutor, postExecutionThread);
        mFactory = factory;
        mRegisterModel = registerFormDomainModel;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mFactory.registerUser(mRegisterModel);
    }
}
