package com.codesignet.consalto.feedback.view;

import com.codesignet.consalto.feedback.pojo.Review;

/**
 * Created by Aya on 30/05/2018.
 */

public interface FeedbackPresenterInterface {
    public void sendReview (Review review,boolean rateFlagFirstTime);
    public void returnResultFromPresenter(int result);
    public void returnRateFLag(boolean rateFlagFirstTime);
    public void existKeyPresenter(boolean keyFlag,int reviewId);
    public void callInteractor();
}
