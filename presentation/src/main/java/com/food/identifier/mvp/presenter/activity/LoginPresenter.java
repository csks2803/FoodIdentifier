package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;

import com.food.identifier.R;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.ILoginView;
import com.food.identifier.mvp.model.UserHolder;
import com.food.identifier.mvp.model.UserPresenterModel;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.other.transformer.DomainToPresenterTransformer;
import com.food.identifier.other.utility.SharedPrefPreferencesWrapper;
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

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.food.identifier.other.Constants.SUCCESS_LOGIN;

/**
 * Created by taras on 12/9/2017.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    private static final int DEFAULT_VALUE = -1;
    @Inject ThreadExecutor mTreadExecutor;
    @Inject PostExecutionThread mPostExecutor;
    @Inject IFoodIdentifierFactory mFoodIdentifierFactory;
    @Inject SharedPrefPreferencesWrapper mSharedPrefPreferencesWrapper;
    @Inject UserHolder mUserHolder;
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

    public void saveLoginState(Activity activity) {
        mSharedPrefPreferencesWrapper.saveToSharedPreferences(activity, SUCCESS_LOGIN, true);
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
            TransformUserToPresenterSubscriber subscriber = new TransformUserToPresenterSubscriber();

            createUserPresenterModel(userDomainModel).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);

            mComposeSubscription.add(subscriber);
        }
    }

    @NonNull
    private Observable<UserPresenterModel> createUserPresenterModel(final UserDomainModel userDomainModel) {
        return Observable.create(new Observable.OnSubscribe<UserPresenterModel>() {
            @Override
            public void call(Subscriber<? super UserPresenterModel> subscriber) {

                DomainToPresenterTransformer transformer = new DomainToPresenterTransformer();
                UserPresenterModel userPresenterModel = transformer.transformUserModel(userDomainModel);
                subscriber.onNext(userPresenterModel);
                subscriber.onCompleted();
            }
        });
    }

    private class TransformUserToPresenterSubscriber extends DefaultSubscriber<UserPresenterModel> {

        @Override
        public void onNext(UserPresenterModel userPresenterModel) {
            mView.saveLoginState();
            mUserHolder.setUserPresenterModel(userPresenterModel);

            LoginSuccess loginSuccess = new LoginSuccess();
            loginSuccess.setRole(userPresenterModel.getType());

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
