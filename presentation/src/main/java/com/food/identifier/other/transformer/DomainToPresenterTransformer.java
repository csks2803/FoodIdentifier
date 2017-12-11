package com.food.identifier.other.transformer;

import com.food.identifier.mvp.model.FeedbackPresentationModel;
import com.food.identifier.mvp.model.ProductCharacteristicsPresentationModel;
import com.food.identifier.mvp.model.ProductPresentationModel;
import com.food.identifier.mvp.model.UserPresenterModel;
import com.foodidentifier.data.model.UserEntityModel;
import com.foodidentifier.domain.model.FeedbackDomainModel;
import com.foodidentifier.domain.model.ProductCharacteristicsDomainModel;
import com.foodidentifier.domain.model.ProductDomainModel;
import com.foodidentifier.domain.model.UserDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taras on 11/25/2017.
 */

public class DomainToPresenterTransformer {

    public List<ProductPresentationModel> transformProductModelList(List<ProductDomainModel> listEntity) {
        List<ProductPresentationModel> listPresentation = new ArrayList<>();

        for (ProductDomainModel productDomainModel : listEntity) {
            ProductPresentationModel productPresentationModel = transformProductModel(productDomainModel);
            listPresentation.add(productPresentationModel);
        }
        return listPresentation;
    }

    public ProductPresentationModel transformProductModel(ProductDomainModel productDomainModel) {
        ProductPresentationModel presentationModel = new ProductPresentationModel();

        presentationModel.setId(productDomainModel.getId());
        presentationModel.setAbout(productDomainModel.getAbout());

        if (productDomainModel.getListCharacteristics() != null) {
            List<ProductCharacteristicsPresentationModel> productCharacteristicList = getProductCharacteristicList(productDomainModel.getListCharacteristics());

            presentationModel.setListCharacteristics(productCharacteristicList);
        }
        presentationModel.setTitle(productDomainModel.getTitle());
        presentationModel.setImageUrls(productDomainModel.getImageUrls());
        presentationModel.setTradeMark(productDomainModel.getTradeMark());

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

    public UserPresenterModel transformUserModel(UserDomainModel userDomainModel) {
        UserPresenterModel userPresenterModel = new UserPresenterModel();

        userPresenterModel.setId(userDomainModel.getId());
        userPresenterModel.setFirstName(userDomainModel.getFirstName());
        userPresenterModel.setLastName(userDomainModel.getLastName());
        userPresenterModel.setType(userDomainModel.getType());
        userPresenterModel.setImageUrl(userDomainModel.getImageUrl());

        return userPresenterModel;
    }
}
