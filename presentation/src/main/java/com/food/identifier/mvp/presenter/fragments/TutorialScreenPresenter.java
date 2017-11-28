package com.food.identifier.mvp.presenter.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import com.food.identifier.di.annotation.PerActivity;
import com.food.identifier.di.components.FragmentComponent;
import com.food.identifier.mvp.interfaces.fragment.ITutorialScreenView;
import com.food.identifier.mvp.presenter.BasePresenter;

import static com.food.identifier.other.Constants.TUTORIAL_KEY;

/**
 * Created by taras on 9/24/2017.
 */
@PerActivity
public class TutorialScreenPresenter extends BasePresenter<ITutorialScreenView> {

    public TutorialScreenPresenter(FragmentComponent component) {
        component.inject(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {
        mView.bindTitle();
    }

    @Override
    protected void onViewDetach() {

    }

    public void bindTitle(Bundle arguments) {
        if (arguments != null) {
            String result = arguments.getString(TUTORIAL_KEY);

            if (!TextUtils.isEmpty(result)) {
                mView.setTitle(result);
            }
        }
    }
}
