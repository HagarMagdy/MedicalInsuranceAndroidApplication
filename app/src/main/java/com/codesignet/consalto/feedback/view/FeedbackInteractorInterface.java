package com.codesignet.consalto.feedback.view;

import com.codesignet.consalto.feedback.pojo.Review;

/**
 * Created by Aya on 30/05/2018.
 */

public interface FeedbackInteractorInterface {

    public void sendFeedback(Review review,boolean rateFlagFirstTime);
    public void returnResultFromInteractor (int result);
    public void getSharedPrefrence(boolean rateFlagFirstTime);
   public void existKey(boolean keyFlag, int reviewId);
    void callSharedPrefrence();
}
