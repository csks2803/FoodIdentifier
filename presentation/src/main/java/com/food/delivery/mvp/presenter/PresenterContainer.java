package com.food.delivery.mvp.presenter;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 1:04 PM
 * Company: FoodDelivery
 */
public class PresenterContainer {
    private Map<String, BasePresenter> mMapContainer;

    @Inject
    public PresenterContainer() {
        mMapContainer = new HashMap<>();
    }

    /**
     * Saves presenter instance.
     * @param name          {@code String} key for passed presenter.
     * @param basePresenter {@link BasePresenter} instance to store.
     */
    public void putPresenter(@Nullable String name, @NonNull BasePresenter basePresenter) {
        mMapContainer.put(name, basePresenter);
    }

    /**
     * Returns presenter from map.
     * @param name {@code String} key for presenter to get.
     * @return {@link BasePresenter} from map, if presenter isn't in map returns {@code NULL}
     */
    @Nullable
    public BasePresenter getPresenter(@Nullable String name) {
        return mMapContainer.get(name);
    }

}
