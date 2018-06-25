package com.codesignet.consalto.feedback.data_access_layer;

import android.content.Context;
import android.util.Log;

import com.codesignet.consalto.feedback.control.ApiRetrofitUtils;
import com.codesignet.consalto.feedback.control.FeedbackServiceRetrofit;
import com.codesignet.consalto.feedback.pojo.ResponseMessage;
import com.codesignet.consalto.feedback.pojo.Review;
import com.codesignet.consalto.feedback.view.FeedbackApiManagerInterface;
import com.codesignet.consalto.feedback.view.FeedbackInteractorInterface;
import com.codesignet.consalto.feedback.view.FeedbackPresenterInterface;
import com.codesignet.consalto.feedback.view.FeedbackSharedPrefrenceInterface;

import me.kartikarora.potato.Potato;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aya on 30/05/2018.
 */

public class FeedbackApiManager implements FeedbackApiManagerInterface{
    FeedbackInteractorInterface interactor;
    FeedbackServiceRetrofit reviewApiMethods;
    FeedbackSharedPrefrenceInterface sharedPrefrenceObject;
    Context context;
    int returnValue=-1;
    public FeedbackApiManager(FeedbackInteractorInterface interactor,Context context) {
        this.interactor = interactor;
        this.context=context;
    }

    @Override
    public void receiveData(Review review) {
        Call<ResponseMessage> responseCall = ApiRetrofitUtils.getService().postReview(review);
        responseCall.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.isSuccessful()) {
                    ResponseMessage responseReturn = response.body();
                    Log.i("success",responseReturn.getMessage().toString());
                    if(responseReturn.getMessage().toString().equals("review added"))
                    {
                        returnValue=0;
                    }
                    else{
                        returnValue=1;
                    }
                    resetResult();
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.i("result",t.getMessage());
                returnValue=1;
                resetResult();
            }
        });

    }

    @Override
    public void resetResult() {
        interactor.returnResultFromInteractor(returnValue);
    }


}
