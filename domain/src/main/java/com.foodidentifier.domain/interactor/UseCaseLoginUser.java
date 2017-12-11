package com.foodidentifier.domain.interactor;

import com.foodidentifier.domain.executor.PostExecutionThread;
import com.foodidentifier.domain.executor.ThreadExecutor;
import com.foodidentifier.domain.net.IFoodIdentifierFactory;

import rx.Observable;

/**
 * Created by taras on 11/11/2017.
 */

public class UseCaseLoginUser extends UseCase {

    private final IFoodIdentifierFactory mFactory;
    private final String mLogin;
    private final String mPassword;

    public UseCaseLoginUser(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IFoodIdentifierFactory factory, String login,String password) {
        super(threadExecutor, postExecutionThread);
        mFactory = factory;
        mLogin = login;
        mPassword = password;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mFactory.loginUser(mLogin,mPassword);
    }
}
