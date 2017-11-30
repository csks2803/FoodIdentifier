package com.food.identifier.mvp.interfaces.fragment;

import com.food.identifier.mvp.interfaces.activity.IBaseView;
import com.food.identifier.mvp.model.ProductPresentationModel;

import java.util.List;

/**
 * Created by taras on 11/23/2017.
 */

public interface IHistoryView extends IBaseView {

    void configureRecycleView();

    void setAdapter(List<ProductPresentationModel> productPresentationModels);

    void hideProgress();

    void showProgress();

    void hideNoHistory();

    void showNoHistory();

    void showNoConnectionToast();

    void replaceToProductScreen();
}
