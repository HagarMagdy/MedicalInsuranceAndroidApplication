package com.codesignet.consalto.home.Home.RetrofitControl;


import com.codesignet.consalto.home.Home.Pojos.ClinicListPojo;
import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalListPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabListPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyListPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;
import com.codesignet.consalto.home.Home.Pojos.SearchPojoList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hoda.CO on 09/04/2018.
 */

public interface Service {
    @GET("getAll")
    @Headers("Content-Type:application/json")
    Call<HospitalListPojo> getAllHospitals();

    @GET("getAll")
    @Headers("Content-Type:application/json")
    Call<ClinicListPojo> getAllClinics();

    @GET("getAll")
    @Headers("Content-Type:application/json")
    Call<LabListPojo> getAllLabs();

    @GET("getAll")
    @Headers("Content-Type:application/json")
    Call<PharmacyListPojo> getAllPharmacy();

    @GET("getAll/{str}")
    @Headers("Content-Type:application/json")
    Call<SearchPojoList> performSearch(@Path("str")String str_search);

    @GET("get")
    @Headers("Content-Type:application/json")
    Call<HospitalPojo> getServiceHospital(@Query("tid") int tid, @Query("sid") int sid);

    @GET("get")
    @Headers("Content-Type:application/json")
    Call<ClinicPojo> getServiceClinic(@Query("tid") int tid, @Query("sid") int sid);

    @GET("get")
    @Headers("Content-Type:application/json")
    Call<PharmacyPojo> getServicePharmacy(@Query("tid") int tid, @Query("sid") int sid);

    @GET("get")
    @Headers("Content-Type:application/json")
    Call<LabPojo> getServiceLab(@Query("tid") int tid, @Query("sid") int sid);
}
