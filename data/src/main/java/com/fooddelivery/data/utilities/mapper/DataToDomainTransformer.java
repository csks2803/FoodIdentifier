package com.fooddelivery.data.utilities.mapper;

import com.fooddelivery.data.model.FeedbackEntityModel;
import com.fooddelivery.data.model.ProductEntityCharacteristicsModel;
import com.fooddelivery.data.model.ProductEntityModel;
import com.fooddelivery.domain.model.FeedbackDomainModel;
import com.fooddelivery.domain.model.ProductCharacteristicsDomainModel;
import com.fooddelivery.domain.model.ProductDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taras on 11/11/2017.
 */

public class DataToDomainTransformer {

    public ProductDomainModel transformStoreModel(ProductEntityModel productEntityModel) {
        ProductDomainModel productDomain = new ProductDomainModel();

        productDomain.setId(productEntityModel.getId());
        productDomain.setAbout(productEntityModel.getAbout());

        List<ProductCharacteristicsDomainModel> productCharacteristicList = getProductCharacteristicList(productEntityModel.getListCharacteristics());
        productDomain.setListCharacteristics(productCharacteristicList);
        productDomain.setTitle(productEntityModel.getTitle());
        productDomain.setImageUrls(productEntityModel.getImageUrls());

        List<FeedbackDomainModel> listFeedbackDomain = getListFeedbackDomain(productEntityModel.getFeedback());
        productDomain.setFeedback(listFeedbackDomain);

        return productDomain;
    }

    private List<FeedbackDomainModel> getListFeedbackDomain(List<FeedbackEntityModel> feedback) {
        List<FeedbackDomainModel> feedbackDomainModelList = new ArrayList<>();

        for (FeedbackEntityModel feedbackEntityModel : feedback) {
            FeedbackDomainModel feedbackDomainModel = new FeedbackDomainModel();

            feedbackDomainModel.setFeedback(feedbackEntityModel.getFeedback());
            feedbackDomainModel.setUserId(feedbackEntityModel.getUserId());
            feedbackDomainModel.setFeedbackId(feedbackEntityModel.getFeedbackId());
            feedbackDomainModel.setProfileUrl(feedbackEntityModel.getProfileUrl());

            feedbackDomainModelList.add(feedbackDomainModel);
        }
        return feedbackDomainModelList;
    }

    private List<ProductCharacteristicsDomainModel> getProductCharacteristicList(List<ProductEntityCharacteristicsModel> listCharacteristics) {
        List<ProductCharacteristicsDomainModel> productCharacteristicsDomainModels = new ArrayList<>();

        for (ProductEntityCharacteristicsModel characteristicEntity : listCharacteristics) {

            ProductCharacteristicsDomainModel productCharacteristicsDomainModel = new ProductCharacteristicsDomainModel();
            productCharacteristicsDomainModel.setDescription(characteristicEntity.getDescription());
            productCharacteristicsDomainModel.setTitle(characteristicEntity.getTitle());

            productCharacteristicsDomainModels.add(productCharacteristicsDomainModel);
        }

        return productCharacteristicsDomainModels;
    }
}
