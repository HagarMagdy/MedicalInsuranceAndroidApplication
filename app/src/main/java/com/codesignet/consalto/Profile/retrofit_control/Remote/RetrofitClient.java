package com.codesignet.consalto.Profile.retrofit_control.Remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aya on 5/31/2018.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            HttpLoggingInterceptor interceptor2 = new HttpLoggingInterceptor();
            interceptor2.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client2 = new OkHttpClient.Builder().addInterceptor(interceptor2).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()).client(client2)
                    .build();
        }
        return retrofit;
    }
}
