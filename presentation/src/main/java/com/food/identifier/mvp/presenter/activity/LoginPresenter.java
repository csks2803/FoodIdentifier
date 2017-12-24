package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;

import com.food.identifier.R;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.ILoginView;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.other.utility.Utility;
import com.foodidentifier.data.exceptions.NotValidCredentialException;
import com.foodidentifier.domain.executor.PostExecutionThread;
import com.foodidentifier.domain.executor.ThreadExecutor;
import com.foodidentifier.domain.interactor.DefaultSubscriber;
import com.foodidentifier.domain.interactor.UseCase;
import com.foodidentifier.domain.interactor.UseCaseLoginUser;
import com.foodidentifier.domain.model.UserDomainModel;
import com.foodidentifier.domain.net.IFoodIdentifierFactory;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

import static com.food.identifier.other.Constants.ORGANIZATION_TYPE;
import static com.food.identifier.other.Constants.USER_TYPE;

/**
 * Created by taras on 12/9/2017.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    public static final int DEFAULT_VALUE = -1;
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
        mView.configureToolbar();
    }

    @Override
    protected void onViewDetach() {
        mComposeSubscription.unsubscribe();
    }

    public void signUpClick() {
        mView.replaceToSignUp();
    }

    public void loginClick(Editable email, Editable password) {
        boolean isEmailValid = emailValidation(email);
        boolean isPasswordValid = passwordValidation(password);

        if (isEmailValid && isPasswordValid) {
            mView.showLoading();
            login(email.toString(), password.toString());
        }
    }

    private boolean passwordValidation(Editable password) {
        int error = DEFAULT_VALUE;

        if (!TextUtils.isEmpty(password) && !Utility.passwordValidate(password.toString())) {
            error = R.string.please_write_correct_password;
        } else if (TextUtils.isEmpty(password)) {
            error = R.string.empty_password;
        }

        if (error == DEFAULT_VALUE) {
            return true;
        } else {
            mView.showPasswordValidationError(error);
        }

        return false;
    }

    private boolean emailValidation(Editable email) {
        int error = DEFAULT_VALUE;
        if (!TextUtils.isEmpty(email) && !Utility.emailValidate(email.toString())) {
            error = R.string.please_write_correct_email;
        } else if (TextUtils.isEmpty(email)) {
            error = R.string.empty_email;
        }

        if (error == DEFAULT_VALUE) {
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
            if (e instanceof NotValidCredentialException) {
                mView.showError(e.getMessage());
            }
        }

        @Override
        public void onNext(UserDomainModel userDomainModel) {
            LoginSuccess loginSuccess = new LoginSuccess();
            loginSuccess.setRole(userDomainModel.getType());

            EventBus.getDefault().post(loginSuccess);
        }
    }
    //endregion

    public static class LoginSuccess {
        private int role;

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }
    }
}
