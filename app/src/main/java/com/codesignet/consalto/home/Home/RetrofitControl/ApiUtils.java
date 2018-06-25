package com.codesignet.consalto.home.Home.RetrofitControl;

/**
 * Created by hoda.CO on 09/04/2018.
 */

public class ApiUtils {
    public static final String BASE_URL =  "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/hospital/";
    public static final String BASE_URL2 = "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/pharmacy/";
    public static final String BASE_URL3 = "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/clinic/";
    public static final String BASE_URL4 = "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/lab/";
    public static final String BASE_URL5 = "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/search/";
    public static final String BASE_URL6 = "http://30.0.0.14:4048/MedicalInsuranceSystem/api/version1/details/";

    public static Service getHospitalService() {

        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
    public static Service getPharmacyService() {

        return RetrofitClient.getClient2(BASE_URL2).create(Service.class);
    }
    public static Service getClinicService() {

        return RetrofitClient.getClient3(BASE_URL3).create(Service.class);
    }
    public static Service getLabService() {

        return RetrofitClient.getClient4(BASE_URL4).create(Service.class);
   }
    public static Service PerformSearch() {

        return RetrofitClient.getClient5(BASE_URL5).create(Service.class);
    }
    public static Service getService() {

        return RetrofitClient.getClient6(BASE_URL6).create(Service.class);
    }
}
