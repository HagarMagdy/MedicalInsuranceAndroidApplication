package com.codesignet.consalto.login.data_access_layer.retrofit_control;

import android.content.Context;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hagar on 30/05/2018.
 */

public class RetrofitClient {
    public static Context context=null;
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(context.getCacheDir(), cacheSize);

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().cache(cache).addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)

                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
