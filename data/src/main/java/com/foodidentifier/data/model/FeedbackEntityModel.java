package com.foodidentifier.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taras on 11/25/2017.
 */

public class FeedbackEntityModel {
    @SerializedName("userId")
    private int userId;
    @SerializedName("feedbackId")
    private int feedbackId;
    @SerializedName("feedback")
    private String feedback;
    @SerializedName("userName")
    private String userName;
    @SerializedName("profileUrl")
    private String profileUrl;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }
}
