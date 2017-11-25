package com.food.identifier.mvp.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.food.identifier.di.IHasComponent;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.di.components.FragmentComponent;
import com.food.identifier.di.module.FragmentModule;
import com.food.identifier.mvp.interfaces.activity.IBaseView;
import com.food.identifier.mvp.presenter.PresenterContainer;
import com.food.identifier.navigator.Navigator;

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
