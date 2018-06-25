package com.codesignet.consalto.suggestion.control;

import com.codesignet.consalto.suggestion.pojo.ResponseMessage;
import com.codesignet.consalto.suggestion.pojo.Suggestion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Mayada Saleh on 6/1/2018.
 */

public interface SuggestionServiceRetrofit {

    @POST("add")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<ResponseMessage> addSuggestion (@Body Suggestion suggest);

}
