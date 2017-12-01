package com.foodidentifier.domain.interactor;

import com.foodidentifier.domain.executor.PostExecutionThread;
import com.foodidentifier.domain.executor.ThreadExecutor;
import com.foodidentifier.domain.net.IFoodIdentifierFactory;

import rx.Observable;

/**
 * Created by taras on 11/11/2017.
 */

public class UseCaseGetProductById extends UseCase {

    private final IFoodIdentifierFactory mFactory;
    private final String mId;

    public UseCaseGetProductById(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IFoodIdentifierFactory factory, String id) {
        super(threadExecutor, postExecutionThread);
        mFactory = factory;
        mId = id;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mFactory.getProductById(mId);
    }
}
