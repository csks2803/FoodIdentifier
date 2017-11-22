package com.food.delivery.mvp.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.food.delivery.di.IHasComponent;
import com.food.delivery.di.components.ActivityComponent;
import com.food.delivery.di.components.FragmentComponent;
import com.food.delivery.di.module.FragmentModule;
import com.food.delivery.mvp.interfaces.activity.IBaseView;
import com.food.delivery.mvp.presenter.PresenterContainer;
import com.food.delivery.mvp.view.activity.BaseActivity;
import com.food.delivery.navigator.Navigator;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Created by Taras Matolinets
 * Data: 9/17/2017.
 * Time: 9:46 PM
 * Company: FoodDelivery
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    @Inject protected PresenterContainer mPresenterContainer;
    @Inject public Navigator mNavigator;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onPresenterInit();
    }

    protected abstract void onPresenterInit();

    @SuppressWarnings("unchecked")
    public FragmentComponent createFragmentComponent() {
        if (getActivity() instanceof IHasComponent) {
            return ((IHasComponent<ActivityComponent>) getActivity()).getComponent().plus(new FragmentModule(getActivity() ));
        } else {
            return null;
        }
    }
}
