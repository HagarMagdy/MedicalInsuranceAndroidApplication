package com.codesignet.consalto.feedback.control;

/**
 * Created by Aya on 31/05/2018.
 */

public class ApiRetrofitUtils {
    public static final String BASE_URL = "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/reviews/";

    public static FeedbackServiceRetrofit getService() {

        return RetrofitCreation.getClient(BASE_URL).create(FeedbackServiceRetrofit.class);
    }


}
