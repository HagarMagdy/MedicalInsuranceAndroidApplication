package com.codesignet.consalto.feedback.view;

/**
 * Created by Aya on 31/05/2018.
 */

public interface FeedbackSharedPrefrenceInterface {
    public void saveSharedPrefrenceRateFlag(boolean rateFlag, int reviewId);
    public void getSharedPrefrenceRateFlag();
    public void isContain();
}
