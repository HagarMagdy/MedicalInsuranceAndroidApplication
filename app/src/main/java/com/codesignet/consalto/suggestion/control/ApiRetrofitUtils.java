package com.codesignet.consalto.suggestion.control;

/**
 * Created by Mayada Saleh on 6/1/2018.
 */

public class ApiRetrofitUtils {

    public static final String BASE_URL = "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/suggestion/";

    public static SuggestionServiceRetrofit getService() {
        return RetrofitCreation.getClient(BASE_URL).create(SuggestionServiceRetrofit.class);
    }

}
