package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.food.identifier.R;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.IRegisterView;
import com.food.identifier.mvp.model.RegisterFormPresenterModel;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.other.transformer.PresenterToDataTransformer;
import com.food.identifier.other.utility.Utility;
import com.foodidentifier.data.exceptions.NotValidOnranizationException;
import com.foodidentifier.domain.executor.PostExecutionThread;
import com.foodidentifier.domain.executor.ThreadExecutor;
import com.foodidentifier.domain.interactor.DefaultSubscriber;
import com.foodidentifier.domain.interactor.UseCase;
import com.foodidentifier.domain.interactor.UseCaseRegisterUser;
import com.foodidentifier.domain.model.RegisterFormDomainModel;
import com.foodidentifier.domain.net.IFoodIdentifierFactory;

import javax.inject.Inject;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by taras on 12/11/2017.
 */

public class RegisterPresenter extends BasePresenter<IRegisterView> {
    public static final int DEFAULT_VALUE = -1;
    @Inject ThreadExecutor mTreadExecutor;
    @Inject PostExecutionThread mPostExecutor;
    @Inject IFoodIdentifierFactory mFoodIdentifierFactory;
    private CompositeSubscription mComposeSubscription;

    public RegisterPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        mComposeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onViewAttach(Activity activity) {
    }

    @Override
    protected void onViewDetach() {
        mComposeSubscription.unsubscribe();
    }

    public void registerUser(final Activity activity, final String login, final String password, final String firstName, final String lastName) {
        boolean isEmailValid = emailValidation(activity, login);
        boolean isPasswordValid = passwordValidation(activity, password);
        boolean isValidFirstName = validateFirstName(activity, firstName);
        boolean isValidLastName = validateLastName(activity, lastName);

        if (isEmailValid && isPasswordValid && isValidFirstName && isValidLastName) {
            TransformRegisterSubscriber subscriber = new TransformRegisterSubscriber();

            mComposeSubscription.add(subscriber);

            getRegisterFormDomainModelObservable(login, password, firstName, lastName).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }

    private boolean validateLastName(Activity activity, String lastName) {
        if (!TextUtils.isEmpty(lastName)) {
            return true;
        } else {
            String error = activity.getString(R.string.empty_last_name);
            mView.showLastNameValidationError(error);
        }
        return false;
    }

    private boolean validateFirstName(Activity activity, String firstName) {
        if (!TextUtils.isEmpty(firstName)) {
            return true;
        } else {
            String error = activity.getString(R.string.empty_first_name);
            mView.showFirstNameValidationError(error);
        }
        return false;
    }

    private boolean passwordValidation(Activity activity, String password) {
        String error = null;

        if (!TextUtils.isEmpty(password) && !Utility.passwordValidate(password)) {
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

    private boolean emailValidation(Activity activity, String email) {
        String error = null;
        if (!TextUtils.isEmpty(email) && Utility.emailValidate(email)) {
            error = activity.getString(R.string.please_write_correct_email);
        } else if (TextUtils.isEmpty(email)) {
            error = activity.getString(R.string.empty_email);
        }

        if (TextUtils.isEmpty(error)) {
            return true;
        } else {
            mView.showLoginValidationError(error);
        }

        return false;
    }

    @NonNull
    private Observable<RegisterFormDomainModel> getRegisterFormDomainModelObservable(final String login, final String password, final String firstName, final String lastName) {
        return Observable.create(new OnSubscribe<RegisterFormDomainModel>() {
            @Override
            public void call(Subscriber<? super RegisterFormDomainModel> subscriber) {
                RegisterFormPresenterModel registerFormPresenterModel = new RegisterFormPresenterModel();
                registerFormPresenterModel.setFirstName(firstName);
                registerFormPresenterModel.setLastName(lastName);
                registerFormPresenterModel.setLogin(login);
                registerFormPresenterModel.setPassword(password);

                PresenterToDataTransformer transformer = new PresenterToDataTransformer();
                RegisterFormDomainModel registerFormDomainModel = transformer.transformRegistrationForm(registerFormPresenterModel);
                subscriber.onNext(registerFormDomainModel);
            }
        });
    }

    public void showProgress() {
        mView.showProgress();
    }

    public class UseCaseRegisterSubscriber extends DefaultSubscriber<Void> {
        @Override
        public void onCompleted() {
            mView.hideProgress();
            mView.hideScreen();
        }

        @Override
        public void onError(Throwable e) {
            mView.hideProgress();
        }
    }

    private class TransformRegisterSubscriber extends DefaultSubscriber<RegisterFormDomainModel> {

        @Override
        public void onError(Throwable e) {
            if (e instanceof NotValidOnranizationException) {
                mView.showLoginValidationError(e.getMessage());
            }
        }

        @Override
        public void onNext(RegisterFormDomainModel registerFormDomainModel) {
            UseCaseRegisterSubscriber subscriber = new UseCaseRegisterSubscriber();

            UseCase useCase = new UseCaseRegisterUser(mTreadExecutor, mPostExecutor, mFoodIdentifierFactory, registerFormDomainModel);
            useCase.execute(subscriber);

            mComposeSubscription.add(subscriber);
        }
    }
}
