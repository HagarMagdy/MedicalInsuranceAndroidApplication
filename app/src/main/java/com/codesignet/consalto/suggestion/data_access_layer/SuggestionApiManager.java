package com.codesignet.consalto.suggestion.data_access_layer;

import android.util.Log;

import com.codesignet.consalto.suggestion.control.ApiRetrofitUtils;
import com.codesignet.consalto.suggestion.pojo.ResponseMessage;
import com.codesignet.consalto.suggestion.pojo.Suggestion;
import com.codesignet.consalto.suggestion.view.SuggestionApiManagerInterface;
import com.codesignet.consalto.suggestion.view.SuggestionInteractorInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mayada Saleh on 6/1/2018.
 */

public class SuggestionApiManager implements SuggestionApiManagerInterface{

    private SuggestionInteractorInterface myInteractor;
    Boolean returnResponseSuggestion=false;

    public SuggestionApiManager(SuggestionInteractorInterface interactor) {
        this.myInteractor = interactor;
    }

    @Override
    public void receiveSuggestion(Suggestion suggest) {

        Log.i("mayadaaa","addressApi"+suggest.getAddress());
        Log.i("mayadaaa","addressApi"+suggest.getContactPhone());
        Log.i("mayadaaa","addressApi"+suggest.getDate());
        Log.i("mayadaaa","addressApi"+suggest.getSupervisor());
        Log.i("mayadaaa","addressApi"+suggest.getMedicalServiceNameAr());
        Log.i("mayadaaa","addressApi"+suggest.getMedicalServiceNameEn());
        Log.i("mayadaaa","addressApi"+suggest.getLatitude());
        Log.i("mayadaaa","addressApi"+suggest.getLongitude());
        Log.i("mayadaaa","addressApi"+suggest.getEmployeeId());
        Log.i("mayadaaa","addressApi"+suggest.getSuggestId());
        Log.i("mayadaaa","addressApi"+suggest.getDescription());
        Log.i("mayadaaa","addressApi"+suggest.getMedicalType());


        Call<ResponseMessage> responseCall = ApiRetrofitUtils.getService().addSuggestion(suggest);
        responseCall.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                    ResponseMessage responseReturn = response.body();
                Log.i("resss",responseReturn.getMessage().toString());
                returnResponseSuggestion=responseReturn.getStatus();
                myInteractor.recieveSuggestionResponseInteractor(returnResponseSuggestion);
            }
            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.i("failure",t.getMessage());
                returnResponseSuggestion=false;
                myInteractor.recieveSuggestionResponseInteractor(returnResponseSuggestion);
            }
        });
    }

}
