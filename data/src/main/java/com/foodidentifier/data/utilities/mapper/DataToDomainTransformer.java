package com.foodidentifier.data.utilities.mapper;

import com.foodidentifier.data.model.CharacteristicEntityModel;
import com.foodidentifier.data.model.FeedbackEntityModel;
import com.foodidentifier.data.model.ProductEntityModel;
import com.foodidentifier.data.model.UserEntityModel;
import com.foodidentifier.domain.model.FeedbackDomainModel;
import com.foodidentifier.domain.model.ProductCharacteristicsDomainModel;
import com.foodidentifier.domain.model.ProductDomainModel;
import com.foodidentifier.domain.model.UserDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taras on 11/11/2017.
 */

public class DataToDomainTransformer {

    public List<ProductDomainModel> transformProductModelList(List<ProductEntityModel> listEntity) {
        List<ProductDomainModel> listDomain = new ArrayList<>();

        for (ProductEntityModel productEntityModel : listEntity) {
            ProductDomainModel productDomainModel = transformProductModel(productEntityModel);
            listDomain.add(productDomainModel);
        }
        return listDomain;
    }

    public ProductDomainModel transformProductModel(ProductEntityModel productEntityModel) {
        ProductDomainModel productDomain = new ProductDomainModel();

        productDomain.setId(productEntityModel.getId());
        productDomain.setAbout(productEntityModel.getAbout());

        if (productEntityModel.getСharacteristic() != null) {
            List<ProductCharacteristicsDomainModel> productCharacteristicList = getProductCharacteristicList(productEntityModel.getСharacteristic());
            productDomain.setListCharacteristics(productCharacteristicList);
        }

        productDomain.setTitle(productEntityModel.getTitle());
        productDomain.setTradeMark(productEntityModel.getTradeMark());
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

    private List<ProductCharacteristicsDomainModel> getProductCharacteristicList(List<CharacteristicEntityModel> listCharacteristics) {
        List<ProductCharacteristicsDomainModel> productCharacteristicsDomainModels = new ArrayList<>();

        for (CharacteristicEntityModel characteristicEntity : listCharacteristics) {

            ProductCharacteristicsDomainModel productCharacteristicsDomainModel = new ProductCharacteristicsDomainModel();
            productCharacteristicsDomainModel.setDescription(characteristicEntity.getDescription());
            productCharacteristicsDomainModel.setTitle(characteristicEntity.getTitle());

            productCharacteristicsDomainModels.add(productCharacteristicsDomainModel);
        }

        return productCharacteristicsDomainModels;
    }

    public UserDomainModel transformUserModel(UserEntityModel userEntity) {
        UserDomainModel userDomainModel = new UserDomainModel();

        userDomainModel.setId(userEntity.getId());
        userDomainModel.setFirstName(userEntity.getFirstName());
        userDomainModel.setLastName(userEntity.getLastName());
        userDomainModel.setType(userEntity.getType());
        userDomainModel.setImageUrl(userEntity.getImageUrl());

        return userDomainModel;
    }
}
