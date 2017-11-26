package com.food.identifier.mvp.interfaces.fragment;


import com.food.identifier.mvp.interfaces.activity.IBaseView;
import com.food.identifier.mvp.model.FeedbackPresentationModel;

import java.util.List;

/**
 * Created by taras on 11/24/2017.
 */

public interface IProductFeedbackView extends IBaseView {
    void setupRecycleView();
    void setupAdapter(List<FeedbackPresentationModel> listCharacteristics);
}
