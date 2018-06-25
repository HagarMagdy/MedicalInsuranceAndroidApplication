package com.codesignet.consalto.feedback.data_access_layer;

import android.content.Context;
import android.util.Log;

import com.codesignet.consalto.feedback.pojo.Review;
import com.codesignet.consalto.feedback.view.FeedbackApiManagerInterface;
import com.codesignet.consalto.feedback.view.FeedbackInteractorInterface;
import com.codesignet.consalto.feedback.view.FeedbackPresenterInterface;
import com.codesignet.consalto.feedback.view.FeedbackSharedPrefrenceInterface;

/**
 * Created by Aya on 30/05/2018.
 */

public class FeedbackInteractor implements FeedbackInteractorInterface{

    FeedbackPresenterInterface presenterObject;
    FeedbackApiManagerInterface DALObject;
    FeedbackSharedPrefrenceInterface sharedPrefrenceObject;
    Context context;
    boolean rateFlagFirstTime=false;
    int reviewId = 0;
    public FeedbackInteractor(FeedbackPresenterInterface presenterObject,Context context) {
        this.presenterObject= presenterObject;
        this.context=context;
        this.DALObject=new FeedbackApiManager(this,context);
        this.sharedPrefrenceObject=new FeedbackSharedPrefrence(context,this);
    }


    @Override
    public void sendFeedback(Review review,boolean rateFlagFirstTime) {
        this.rateFlagFirstTime=rateFlagFirstTime;
        this.reviewId=review.getServiceId();
        DALObject.receiveData(review);
    }

    @Override
    public void returnResultFromInteractor(int result) {
        if(result==0)
        {
            sharedPrefrenceObject.saveSharedPrefrenceRateFlag(rateFlagFirstTime,reviewId);
        }
        presenterObject.returnResultFromPresenter(result);
    }

    @Override
    public void getSharedPrefrence(boolean rateFlagFirstTime) {
        presenterObject.returnRateFLag(rateFlagFirstTime);
    }

    @Override
    public void existKey(boolean keyFlag,int reviewId) {
        presenterObject.existKeyPresenter(keyFlag,reviewId);

    }

    @Override
    public void callSharedPrefrence() {
        sharedPrefrenceObject.isContain();
    }
}
