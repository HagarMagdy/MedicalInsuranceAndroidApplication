package com.codesignet.consalto.forget_password.control;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mayada Saleh on 6/2/2018.
 */

public class RetrofitCreation {


    public static Retrofit getClient(String baseUrl) {
        Retrofit retrofit = null;
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
