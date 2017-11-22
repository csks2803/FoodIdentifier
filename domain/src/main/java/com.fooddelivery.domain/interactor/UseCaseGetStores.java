package com.fooddelivery.domain.interactor;

import com.fooddelivery.domain.executor.PostExecutionThread;
import com.fooddelivery.domain.executor.ThreadExecutor;
import com.fooddelivery.domain.net.IFoodDeliveryFactory;

import rx.Observable;

/**
 * Created by taras on 11/11/2017.
 */

public class UseCaseGetStores extends UseCase {

    private final IFoodDeliveryFactory mFactory;
    private final double mLongitude;
    private final double mLatitude;

    public UseCaseGetStores(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IFoodDeliveryFactory bridge, double latitude, double longitude) {
        super(threadExecutor, postExecutionThread);
        mFactory = bridge;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mFactory.getStoreDomainModel(mLatitude, mLongitude);
    }
}
