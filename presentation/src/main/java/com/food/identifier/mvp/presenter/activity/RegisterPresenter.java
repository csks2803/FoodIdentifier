package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.food.identifier.R;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.IRegisterView;
import com.food.identifier.mvp.model.RegisterFormPresenterModel;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.other.Constants;
import com.food.identifier.other.transformer.PresenterToDataTransformer;
import com.food.identifier.other.utility.SharedPrefPreferencesWrapper;
import com.food.identifier.other.utility.Utility;
import com.foodidentifier.data.exceptions.NotValidCredentialException;
import com.foodidentifier.domain.executor.PostExecutionThread;
import com.foodidentifier.domain.executor.ThreadExecutor;
import com.foodidentifier.domain.interactor.DefaultSubscriber;
import com.foodidentifier.domain.interactor.UseCase;
import com.foodidentifier.domain.interactor.UseCaseRegisterUser;
import com.foodidentifier.domain.model.RegisterFormDomainModel;
import com.foodidentifier.domain.net.IFoodIdentifierFactory;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.food.identifier.other.Constants.ORGANIZATION_TYPE;
import static com.food.identifier.other.Constants.SUCCESS_LOGIN;
import static com.food.identifier.other.Constants.USER_TYPE;

/**
 * Created by taras on 12/11/2017.
 */

public class RegisterPresenter extends BasePresenter<IRegisterView> {

    public static final int DEFAULT_VALUE = -1;
    @Inject ThreadExecutor mTreadExecutor;
    @Inject PostExecutionThread mPostExecutor;
    @Inject IFoodIdentifierFactory mFoodIdentifierFactory;
    @Inject SharedPrefPreferencesWrapper mSharedPrefPreferencesWrapper;
    private CompositeSubscription mComposeSubscription;

    public RegisterPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        mComposeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onViewAttach(Activity activity) {
        mView.configureToolbar();
    }

    @Override
    protected void onViewDetach() {
        mComposeSubscription.unsubscribe();
    }

    public void registerUser(final int type, final String login, final String password, final String firstName, final String lastName) {
        boolean isEmailValid = emailValidation(login);
        boolean isPasswordValid = passwordValidation(password);
        boolean isValidFirstName = validateFirstName(firstName);
        boolean isValidLastName = validateLastName(lastName);

        if (isEmailValid && isPasswordValid && isValidFirstName && isValidLastName) {

            mView.showProgress();
            TransformRegisterSubscriber subscriber = new TransformRegisterSubscriber(type);

            mComposeSubscription.add(subscriber);

            createRegisterFormDomainModelObservable(type, login, password, firstName, lastName)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }

    private boolean validateLastName(String lastName) {
        if (!TextUtils.isEmpty(lastName)) {
            return true;
        } else {
            mView.showLastNameValidationError(R.string.empty_last_name);
        }
        return false;
    }

    private boolean validateFirstName(String firstName) {
        if (!TextUtils.isEmpty(firstName)) {
            return true;
        } else {
            mView.showFirstNameValidationError(R.string.empty_first_name);
        }
        return false;
    }

    private boolean passwordValidation(String password) {
        int error = -1;

        if (!TextUtils.isEmpty(password) && !Utility.passwordValidate(password)) {
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

    private boolean emailValidation(String email) {
        int error = DEFAULT_VALUE;
        if (!TextUtils.isEmpty(email) && !Utility.emailValidate(email)) {
            error = R.string.please_write_correct_email;
        } else if (TextUtils.isEmpty(email)) {
            error = R.string.empty_email;
        }

        if (error == DEFAULT_VALUE) {
            return true;
        } else {
            mView.showLoginValidationError(error);
        }

        return false;
    }

    public void saveLoginState(Activity activity) {
        mSharedPrefPreferencesWrapper.saveToSharedPreferences(activity, SUCCESS_LOGIN, true);
    }


    @NonNull
    private Observable<RegisterFormDomainModel> createRegisterFormDomainModelObservable(final int type, final String login, final String password, final String firstName, final String lastName) {
        return Observable.create(new OnSubscribe<RegisterFormDomainModel>() {
            @Override
            public void call(Subscriber<? super RegisterFormDomainModel> subscriber) {
                RegisterFormPresenterModel registerFormPresenterModel = new RegisterFormPresenterModel();
                registerFormPresenterModel.setFirstName(firstName);
                registerFormPresenterModel.setLastName(lastName);
                registerFormPresenterModel.setLogin(login);
                registerFormPresenterModel.setPassword(password);
                registerFormPresenterModel.setType(type);

                PresenterToDataTransformer transformer = new PresenterToDataTransformer();
                RegisterFormDomainModel registerFormDomainModel = transformer.transformRegistrationForm(registerFormPresenterModel);
                subscriber.onNext(registerFormDomainModel);
            }
        });
    }

    public class UseCaseRegisterSubscriber extends DefaultSubscriber<Void> {
        private int type;

        public UseCaseRegisterSubscriber(int type) {
            this.type = type;
        }

        @Override
        public void onCompleted() {
            mView.saveLoginState();
            mView.hideProgress();
            mView.closeScreen();
            RegisterSuccess registerSuccess = new RegisterSuccess();
            registerSuccess.setUserType(type);

            EventBus.getDefault().post(registerSuccess);
        }

        @Override
        public void onError(Throwable e) {
            mView.hideProgress();
        }
    }

    private class TransformRegisterSubscriber extends DefaultSubscriber<RegisterFormDomainModel> {

        private final int type;

        public TransformRegisterSubscriber(int type) {
            this.type = type;
        }

        @Override
        public void onError(Throwable e) {
            if (e instanceof NotValidCredentialException) {
                mView.showError(e.getMessage());
            }
        }

        @Override
        public void onNext(RegisterFormDomainModel registerFormDomainModel) {
            UseCaseRegisterSubscriber subscriber = new UseCaseRegisterSubscriber(type);

            UseCase useCase = new UseCaseRegisterUser(mTreadExecutor, mPostExecutor, mFoodIdentifierFactory, registerFormDomainModel);
            useCase.execute(subscriber);

            mComposeSubscription.add(subscriber);
        }
    }

    public static class RegisterSuccess {
        private int userType;

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }
    }
}
