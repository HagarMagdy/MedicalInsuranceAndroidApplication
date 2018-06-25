package com.codesignet.consalto.feedback.control;

import com.codesignet.consalto.feedback.pojo.ResponseMessage;
import com.codesignet.consalto.feedback.pojo.Review;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Aya on 31/05/2018.
 */

public interface FeedbackServiceRetrofit {
    @POST("insert")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<ResponseMessage> postReview (@Body Review review);
}
