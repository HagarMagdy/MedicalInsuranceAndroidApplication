package com.codesignet.consalto.medical_service_details.retrofit_control;



import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hagar on 31/05/2018.
 */

public interface Service {
    @GET("get")
    Call<HospitalPojo> getHospitalDetails (@Query("tid")int tid , @Query("sid")int sid);

    @GET("get")
    Call<ClinicPojo> getClinicDetails (@Query("tid")int tid , @Query("sid")int sid);

    @GET("get")
    Call<PharmacyPojo> getPharmacyDetails (@Query("tid")int tid , @Query("sid")int sid);

    @GET("get")
    Call<LabPojo> getLabDetails (@Query("tid")int tid , @Query("sid")int sid);

}
