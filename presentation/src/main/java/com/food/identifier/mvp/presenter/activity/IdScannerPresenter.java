package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;

import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.IIdScannerView;
import com.food.identifier.mvp.model.ProductHolder;
import com.food.identifier.mvp.model.ProductPresentationModel;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.other.transformer.DomainToPresenterTransformer;
import com.fooddelivery.domain.executor.PostExecutionThread;
import com.fooddelivery.domain.executor.ThreadExecutor;
import com.fooddelivery.domain.interactor.DefaultSubscriber;
import com.fooddelivery.domain.interactor.UseCase;
import com.fooddelivery.domain.interactor.UseCaseGetProductById;
import com.fooddelivery.domain.model.ProductDomainModel;
import com.fooddelivery.domain.net.IFoodIdentifierFactory;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by taras on 11/23/2017.
 */

public class IdScannerPresenter extends BasePresenter<IIdScannerView> {
    @Inject ThreadExecutor mTreadExecutor;
    @Inject PostExecutionThread mPostExecutor;
    @Inject IFoodIdentifierFactory mFoodIdentifierFactory;
    @Inject ProductHolder mProductHolder;
    private CompositeSubscription mComposeSubscription;

    public IdScannerPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        mComposeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onViewAttach(Activity context) {

    }

    @Override
    protected void onViewDetach() {
        mComposeSubscription.unsubscribe();
    }

    public void sendScannedResult(String id) {
        UseCaseGetProductByIdSubscriber subscriber = new UseCaseGetProductByIdSubscriber();
        mComposeSubscription.add(subscriber);

        UseCase useCaseGetProductById = new UseCaseGetProductById(mTreadExecutor, mPostExecutor, mFoodIdentifierFactory, id);
        useCaseGetProductById.execute(subscriber);
    }

    private class UseCaseGetProductByIdSubscriber extends DefaultSubscriber<ProductDomainModel> {

        @Override
        public void onNext(ProductDomainModel foodPackageDomainModel) {
            super.onNext(foodPackageDomainModel);
            transformDomainToPresenterModel(foodPackageDomainModel);
        }

        private void transformDomainToPresenterModel(final ProductDomainModel foodPackageDomainModel) {
            Observable.create(new Observable.OnSubscribe<ProductPresentationModel>() {
                @Override
                public void call(Subscriber<? super ProductPresentationModel> subscriber) {
                    DomainToPresenterTransformer transformer = new DomainToPresenterTransformer();
                    ProductPresentationModel productPresentation = transformer.transfomProductModel(foodPackageDomainModel);

                    subscriber.onNext(productPresentation);
                }
            }).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ProductPresentationModel>() {
                @Override
                public void onCompleted() {
                    mView.finishLoading();
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(ProductPresentationModel productPresentation) {
                    mProductHolder.setProductHolder(productPresentation);
                    mView.replaceToFoodProduct();
                }
            });
        }
    }
}
