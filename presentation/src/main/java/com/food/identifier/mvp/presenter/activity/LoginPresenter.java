package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;

import com.food.identifier.R;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.ILoginView;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.other.utility.Utility;
import com.foodidentifier.domain.executor.PostExecutionThread;
import com.foodidentifier.domain.executor.ThreadExecutor;
import com.foodidentifier.domain.interactor.DefaultSubscriber;
import com.foodidentifier.domain.interactor.UseCase;
import com.foodidentifier.domain.interactor.UseCaseLoginUser;
import com.foodidentifier.domain.model.UserDomainModel;
import com.foodidentifier.domain.net.IFoodIdentifierFactory;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by taras on 12/9/2017.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    @Inject ThreadExecutor mTreadExecutor;
    @Inject PostExecutionThread mPostExecutor;
    @Inject IFoodIdentifierFactory mFoodIdentifierFactory;
    private CompositeSubscription mComposeSubscription;

    public LoginPresenter(ActivityComponent activityComponent) {
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

    public void signUpClick() {
        mView.replaceToSignUp();
    }

    public void loginClick(Activity activity, Editable email, Editable password) {
        boolean isEmailValid = emailValidation(activity, email);
        boolean isPasswordValid = passwordValidation(activity, password);

        if (isEmailValid && isPasswordValid) {
            mView.showLoading();
            login(email.toString(), password.toString());
        }
    }

    private boolean passwordValidation(Activity activity, Editable password) {
        String error = null;

        if (!TextUtils.isEmpty(password) && !Utility.passwordValidate(password.toString())) {
            error = activity.getString(R.string.please_write_correct_password);
        } else if (TextUtils.isEmpty(password)) {
            error = activity.getString(R.string.empty_password);
        }

        if (TextUtils.isEmpty(error)) {
            return true;
        } else {
            mView.showPasswordValidationError(error);
        }

        return false;
    }

    private boolean emailValidation(Activity activity, Editable email) {
        String error = null;
        if (!TextUtils.isEmpty(email) && !Utility.emailValidate(email.toString())) {
            error = activity.getString(R.string.please_write_correct_email);
        } else if (TextUtils.isEmpty(email)) {
            error = activity.getString(R.string.empty_email);
        }

        if (TextUtils.isEmpty(error)) {
            return true;
        } else {
            mView.showEmailValidationError(error);
        }

        return false;
    }

    private void login(String email, String password) {
        UseCaseLoginUserSubscriber subscriber = new UseCaseLoginUserSubscriber();

        UseCase useCase = new UseCaseLoginUser(mTreadExecutor, mPostExecutor, mFoodIdentifierFactory, email, password);
        useCase.execute(subscriber);

        mComposeSubscription.add(subscriber);
    }

    //region SUBSCRIBER
    private class UseCaseLoginUserSubscriber extends DefaultSubscriber<UserDomainModel> {
        @Override
        public void onCompleted() {
            mView.hideProgress();
            mView.closeScreen();
        }

        @Override
        public void onError(Throwable e) {
            mView.hideProgress();
        }

        @Override
        public void onNext(UserDomainModel userDomainModel) {

        }
    }
    //endregion
}
