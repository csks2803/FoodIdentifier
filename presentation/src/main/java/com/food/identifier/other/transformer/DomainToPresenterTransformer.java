package com.food.identifier.other.transformer;

import com.food.identifier.mvp.model.FeedbackPresentationModel;
import com.food.identifier.mvp.model.ProductCharacteristicsPresentationModel;
import com.food.identifier.mvp.model.ProductPresentationModel;
import com.food.identifier.mvp.presenter.fragments.ProductPresenter;
import com.fooddelivery.data.model.FeedbackEntityModel;
import com.fooddelivery.data.model.ProductEntityCharacteristicsModel;
import com.fooddelivery.data.model.ProductEntityModel;
import com.fooddelivery.domain.model.FeedbackDomainModel;
import com.fooddelivery.domain.model.ProductCharacteristicsDomainModel;
import com.fooddelivery.domain.model.ProductDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taras on 11/25/2017.
 */

public class DomainToPresenterTransformer {

    public ProductPresentationModel transfomProductModel(ProductDomainModel productDomainModel) {
        ProductPresentationModel presentationModel = new ProductPresentationModel();

        presentationModel.setId(productDomainModel.getId());
        presentationModel.setAbout(productDomainModel.getAbout());

        List<ProductCharacteristicsPresentationModel> productCharacteristicList = getProductCharacteristicList(productDomainModel.getListCharacteristics());
        presentationModel.setListCharacteristics(productCharacteristicList);
        presentationModel.setTitle(productDomainModel.getTitle());
        presentationModel.setImageUrls(productDomainModel.getImageUrls());

        List<FeedbackPresentationModel> listFeedbackDomain = getListFeedbackDomain(productDomainModel.getFeedback());
        presentationModel.setFeedback(listFeedbackDomain);

        return presentationModel;
    }

    private List<FeedbackPresentationModel> getListFeedbackDomain(List<FeedbackDomainModel> feedback) {
        List<FeedbackPresentationModel> feedbackDomainModelList = new ArrayList<>();

        for (FeedbackDomainModel feedbackDomainModel : feedback) {
            FeedbackPresentationModel feedbackPresenterModel = new FeedbackPresentationModel();

            feedbackPresenterModel.setFeedback(feedbackDomainModel.getFeedback());
            feedbackPresenterModel.setUserId(feedbackDomainModel.getUserId());
            feedbackPresenterModel.setFeedBackId(feedbackDomainModel.getFeedbackId());
            feedbackPresenterModel.setProfileUrl(feedbackDomainModel.getProfileUrl());

            feedbackDomainModelList.add(feedbackPresenterModel);
        }
        return feedbackDomainModelList;
    }

    private List<ProductCharacteristicsPresentationModel> getProductCharacteristicList(List<ProductCharacteristicsDomainModel> listCharacteristics) {
        List<ProductCharacteristicsPresentationModel> productCharacteristicsPresenterModels = new ArrayList<>();

        for (ProductCharacteristicsDomainModel characteristicDomain : listCharacteristics) {

            ProductCharacteristicsPresentationModel productCharacteristicsDomainModel = new ProductCharacteristicsPresentationModel();
            productCharacteristicsDomainModel.setDescription(characteristicDomain.getDescription());
            productCharacteristicsDomainModel.setTitle(characteristicDomain.getTitle());

            productCharacteristicsPresenterModels.add(productCharacteristicsDomainModel);
        }

        return productCharacteristicsPresenterModels;
    }

}