package com.food.identifier.mvp.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.food.identifier.mvp.interfaces.activity.IBaseView;

/**
 * Created by Taras Matolinets
 * Data: 9/9/2017.
 * Time: 3:51 PM
 * Company: FoodDelivery
 */
public abstract class BasePresenter<T extends IBaseView> {

    /**
     * Attached MVP view instance.
     */
    protected T mView;

    /**
     * Attaches corresponding MVP View to presenter instance.
     *
     * @param view corresponding MVP View to attach.
     */
    public final void attachView(@NonNull T view) {
        mView = view;
        if (view instanceof Activity) {
            onViewAttach((Activity) view);
        } else if (view instanceof Fragment) {
            onViewAttach(((Fragment) view).getActivity());
        }
    }

    /**
     * Detaches attached MVP View from presenter instance.
     */
    public final void detachView() {
        onViewDetach();
        mView = null;
    }

    /**
     * Called on MVP View attach.
     */
    protected abstract void onViewAttach(Activity context);

    /**
     * Called on MVP View detach.
     */
    protected abstract void onViewDetach();

    /**
     * Called when MVP View become visible on the screen.
     */
    public void onViewShown() {
    }

    /**
     * Called when MVP View become invisible on the screen.
     */
    public void onViewHidden() {
    }
}
