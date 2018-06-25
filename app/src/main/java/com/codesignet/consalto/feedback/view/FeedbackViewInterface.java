package com.codesignet.consalto.feedback.view;

/**
 * Created by Aya on 30/05/2018.
 */

public interface FeedbackViewInterface {
    public void takeAction(int returnResult);
    public void returnRateFlag(boolean rateFlag);
    public void existKeyActivity(boolean keyFlag, int reviewId);
    public void callPresenter();
}
