package com.food.delivery.mvp.presenter.fragments;

import android.app.Activity;

import com.food.delivery.di.components.FragmentComponent;
import com.food.delivery.mvp.interfaces.fragment.IStoreListView;
import com.food.delivery.mvp.model.FoodPackageHolder;
import com.food.delivery.mvp.model.FoodPackagePresenterModel;
import com.food.delivery.mvp.presenter.BasePresenter;
import com.food.delivery.other.transformer.DomainToPresenterTransformer;
import com.fooddelivery.domain.executor.PostExecutionThread;
import com.fooddelivery.domain.executor.ThreadExecutor;
import com.fooddelivery.domain.interactor.DefaultSubscriber;
import com.fooddelivery.domain.interactor.UseCase;
import com.fooddelivery.domain.interactor.UseCaseGetStores;
import com.fooddelivery.domain.model.FoodPackageDomainModel;
import com.fooddelivery.domain.net.IFoodDeliveryFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by taras on 11/3/2017.
 */

public class StoreListPresenter extends BasePresenter<IStoreListView> {
    @Inject ThreadExecutor mTreadExecutor;
    @Inject PostExecutionThread mPostExecutor;
    @Inject IFoodDeliveryFactory mFoodDeliveryFactory;
    @Inject FoodPackageHolder mFoodPackageHolder;
    private CompositeSubscription mComposeSubscription;

    public StoreListPresenter(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
        mComposeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onViewAttach(Activity activity) {
        mView.prepareRecycleView();
        mView.prepareAdapter();
        mView.loadStores();
    }

    @Override
    protected void onViewDetach() {
        mComposeSubscription.unsubscribe();
    }

    public void loadStores() {
        UseCaseGeStoreSubscriber useCaseGeStoreSubscriber = new UseCaseGeStoreSubscriber();
        mComposeSubscription.add(useCaseGeStoreSubscriber);

        UseCase useCase = new UseCaseGetStores(mTreadExecutor, mPostExecutor, mFoodDeliveryFactory, mFoodPackageHolder.getLatitude(), mFoodPackageHolder.getLongitude());
        useCase.execute(useCaseGeStoreSubscriber);
    }

    private class UseCaseGeStoreSubscriber extends DefaultSubscriber<List<FoodPackageDomainModel>> {
        @Override
        public void onNext(List<FoodPackageDomainModel> list) {
            transformDomainToPresenter(list);
        }
    }

    private void transformDomainToPresenter(final List<FoodPackageDomainModel> list) {
        Observable.create(new Observable.OnSubscribe<List<FoodPackagePresenterModel>>() {
            @Override
            public void call(Subscriber<? super List<FoodPackagePresenterModel>> subscriber) {
                DomainToPresenterTransformer domainToPresenterTransformer = new DomainToPresenterTransformer();
                List<FoodPackagePresenterModel> listFoodPackage = domainToPresenterTransformer.transform(list);
                subscriber.onNext(listFoodPackage);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<FoodPackagePresenterModel>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<FoodPackagePresenterModel> foodPackagePresenterModels) {
                mView.injectStores(foodPackagePresenterModels);
                mFoodPackageHolder.setListFoodPackagePresenter(foodPackagePresenterModels);
                EventBus.getDefault().post(new OnPackagesLoad());
            }
        });
    }

    public static class OnPackagesLoad {

    }
}
