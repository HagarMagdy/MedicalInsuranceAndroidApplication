package com.codesignet.consalto.feedback.view;

import com.codesignet.consalto.feedback.pojo.Review;

/**
 * Created by Aya on 30/05/2018.
 */

public interface FeedbackApiManagerInterface {
    public void receiveData(Review review);
    public void resetResult();
}
