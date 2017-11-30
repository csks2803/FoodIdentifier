package com.food.identifier.mvp.presenter.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.provider.Settings;

import com.food.identifier.R;
import com.food.identifier.di.components.FragmentComponent;
import com.food.identifier.mvp.interfaces.fragment.IHistoryView;
import com.food.identifier.mvp.model.ProductHolder;
import com.food.identifier.mvp.model.ProductPresentationModel;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.mvp.presenter.activity.ProductPresenter.ChangeToolbarColor;
import com.food.identifier.mvp.view.adapters.HistoryAdapter.HistoryClick;
import com.food.identifier.other.transformer.DomainToPresenterTransformer;
import com.food.identifier.other.utility.CloudHelper;
import com.fooddelivery.domain.executor.PostExecutionThread;
import com.fooddelivery.domain.executor.ThreadExecutor;
import com.fooddelivery.domain.interactor.DefaultSubscriber;
import com.fooddelivery.domain.interactor.UseCase;
import com.fooddelivery.domain.interactor.UseCaseGetProductListByUserId;
import com.fooddelivery.domain.model.ProductDomainModel;
import com.fooddelivery.domain.net.IFoodIdentifierFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.provider.Settings.Secure.ANDROID_ID;

/**
 * Created by taras on 11/23/2017.
 */

public class HistoryPresenter extends BasePresenter<IHistoryView> {
    @Inject ThreadExecutor mTreadExecutor;
    @Inject PostExecutionThread mPostExecutor;
    @Inject IFoodIdentifierFactory mFoodIdentifierFactory;
    @Inject ProductHolder mProductHolder;
    private CompositeSubscription mComposeSubscription;

    public HistoryPresenter(FragmentComponent activityComponent) {
        activityComponent.inject(this);

        mComposeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onViewAttach(Activity context) {
        EventBus.getDefault().register(this);

        ChangeToolbarColor changeToolbarColor = new ChangeToolbarColor();
        changeToolbarColor.setColor(R.color.colorPrimary);
        EventBus.getDefault().post(changeToolbarColor);

        mView.showProgress();
        mView.configureRecycleView();

        if (CloudHelper.isOnline(context)) {
            retrieveProductHistory(context);
        } else {
            mView.showNoConnectionToast();
        }
    }

    private void retrieveProductHistory(Activity context) {
        UseCaseGetProductListSubscriber subscriber = new UseCaseGetProductListSubscriber();
        mComposeSubscription.add(subscriber);

        String id = getUserId(context);
        UseCase useCaseGetProductById = new UseCaseGetProductListByUserId(mTreadExecutor, mPostExecutor, mFoodIdentifierFactory, id);
        useCaseGetProductById.execute(subscriber);
    }

    @SuppressLint("HardwareIds")
    private String getUserId(Activity context) {
        return Settings.Secure.getString(context.getContentResolver(), ANDROID_ID);
    }

    @Override
    protected void onViewDetach() {
        EventBus.getDefault().unregister(this);
        mComposeSubscription.unsubscribe();
    }

    @Subscribe
    public void historyClick(HistoryClick historyClick) {
        mProductHolder.setProductHolder(historyClick.getProductPresentationModel());
        mView.replaceToProductScreen();
    }

    private class UseCaseGetProductListSubscriber extends DefaultSubscriber<List<ProductDomainModel>> {
        @Override
        public void onCompleted() {
            mView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            mView.showNoHistory();
            mView.hideProgress();
        }

        @Override
        public void onNext(List<ProductDomainModel> productPresentationList) {
            if (productPresentationList.isEmpty()) {
                mView.showNoHistory();
                mView.hideProgress();
            } else {
                transformToPresentationModel(productPresentationList);
            }
        }

        private void transformToPresentationModel(final List<ProductDomainModel> productDomainList) {
            Observable.create(new OnSubscribe<List<ProductPresentationModel>>() {
                @Override
                public void call(Subscriber<? super List<ProductPresentationModel>> subscriber) {
                    DomainToPresenterTransformer transformer = new DomainToPresenterTransformer();
                    List<ProductPresentationModel> list = transformer.transformProductModelList(productDomainList);
                    subscriber.onNext(list);
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<ProductPresentationModel>>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    mView.showNoHistory();
                }

                @Override
                public void onNext(List<ProductPresentationModel> productPresentationModels) {
                    mView.setAdapter(productPresentationModels);
                }
            });
        }
    }

}
