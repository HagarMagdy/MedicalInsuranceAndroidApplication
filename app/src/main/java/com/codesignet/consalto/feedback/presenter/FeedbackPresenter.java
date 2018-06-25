package com.codesignet.consalto.feedback.presenter;

import android.content.Context;
import android.util.Log;

import com.codesignet.consalto.feedback.data_access_layer.FeedbackInteractor;
import com.codesignet.consalto.feedback.pojo.Review;
import com.codesignet.consalto.feedback.view.FeedbackInteractorInterface;
import com.codesignet.consalto.feedback.view.FeedbackPresenterInterface;
import com.codesignet.consalto.feedback.view.FeedbackViewInterface;

/**
 * Created by Aya on 30/05/2018.
 */

public class FeedbackPresenter implements FeedbackPresenterInterface {
    FeedbackViewInterface feedbackActivity;
    FeedbackInteractorInterface interactorObject;
    Context context;

    public FeedbackPresenter(FeedbackViewInterface feedbackActivity,Context context) {
        this.feedbackActivity = feedbackActivity;
        this.interactorObject=new FeedbackInteractor(this,context);
        this.context=context;
    }


    @Override
    public void sendReview(Review review,boolean rateFlagFirstTime) {
        interactorObject.sendFeedback(review,rateFlagFirstTime);
    }


    @Override
    public void returnRateFLag(boolean rateFlagFirstTime) {
      feedbackActivity.returnRateFlag(rateFlagFirstTime);
    }

    @Override
    public void existKeyPresenter(boolean keyFlag, int reviewId) {
       feedbackActivity.existKeyActivity(keyFlag,reviewId);
    }

    @Override
    public void callInteractor() {
        interactorObject.callSharedPrefrence();
    }

    @Override
    public void returnResultFromPresenter(int result) {
        feedbackActivity.takeAction(result);
    }
}
