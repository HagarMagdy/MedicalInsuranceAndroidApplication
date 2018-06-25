package com.codesignet.consalto.home.Home.data_access_layer;

import android.util.Log;


import com.codesignet.consalto.home.Home.Pojos.ClinicListPojo;
import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalListPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabListPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyListPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;
import com.codesignet.consalto.home.Home.Pojos.SearchPojo;
import com.codesignet.consalto.home.Home.Pojos.SearchPojoList;
import com.codesignet.consalto.home.Home.RetrofitControl.ApiUtils;
import com.codesignet.consalto.home.Home.RetrofitControl.Service;
import com.codesignet.consalto.home.Home.view.ApiHome_Interface;
import com.codesignet.consalto.home.Home.view.HomeInteractor_Interface;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hoda.CO on 31/05/2018.
 */

public class ApiHomeImpl implements ApiHome_Interface {
    private HomeInteractor_Interface mInteractor;
    ArrayList<HospitalPojo> allhospitals;
    ArrayList<LabPojo> alllabs;
    HospitalPojo hotspital=null;
    ClinicPojo clinic=null;
    PharmacyPojo phamacy=null;
    LabPojo lab=null;
    SearchPojo res=null;

    public ApiHomeImpl(HomeInteractor_Interface mInteractor) {
        this.mInteractor = mInteractor;
    }

    @Override
    public void getAllServices() {
        Log.i("hoda", "getdata");
        getAllHospitals();
        getAllPharmacies();
        getAllClinics();
        getAllLabs();

    }

    public void getAllHospitals() {
        Service ApiHospitalService = ApiUtils.getHospitalService();
        ApiHospitalService.getAllHospitals().enqueue(new Callback<HospitalListPojo>() {
            @Override
            public void onResponse(Call<HospitalListPojo> call, Response<HospitalListPojo> response) {
               Log.i("ayaa", "getAllHospitals"+response.body().getHospitals().get(0).getNameAr());
                allhospitals = response.body().getHospitals();
                for (int i = 0; i < allhospitals.size(); i++) {
                    mInteractor.setHospital(allhospitals.get(i));
                }
            }

            @Override
            public void onFailure(Call<HospitalListPojo> call, Throwable t) {
                Log.i("hoda", "no connect"+t.getMessage());
            }
        });
    }

    public void getAllPharmacies() {
        Service ApiPharmacayService = ApiUtils.getPharmacyService();
        ApiPharmacayService.getAllPharmacy().enqueue(new Callback<PharmacyListPojo>() {
            @Override
            public void onResponse(Call<PharmacyListPojo> call, Response<PharmacyListPojo> response) {
                Log.i("hoda", response.body().getPharmacies().get(0).getNameAr());
                ArrayList<PharmacyPojo> allpharmacies = response.body().getPharmacies();
                for (int i = 0; i < allpharmacies.size(); i++) {
                    mInteractor.setPharmacy(allpharmacies.get(i));
                }
            }

            @Override
            public void onFailure(Call<PharmacyListPojo> call, Throwable t) {
                Log.i("hoda", "no connect");
            }
        });
    }

    public void getAllClinics() {
        Service ApiClinicService = ApiUtils.getClinicService();
        ApiClinicService.getAllClinics().enqueue(new Callback<ClinicListPojo>() {
            @Override
            public void onResponse(Call<ClinicListPojo> call, Response<ClinicListPojo> response) {
                ArrayList<ClinicPojo> allclinics = response.body().getClinics();
                for (int i = 0; i < allclinics.size(); i++) {
                    mInteractor.setClinic(allclinics.get(i));
                }
            }

            @Override
            public void onFailure(Call<ClinicListPojo> call, Throwable t) {
                Log.i("hoda", "no connect");
            }
        });
    }

    public void getAllLabs() {
        Service ApiLabsService = ApiUtils.getLabService();
        ApiLabsService.getAllLabs().enqueue(new Callback<LabListPojo>() {
            @Override
            public void onResponse(Call<LabListPojo> call, Response<LabListPojo> response) {
                alllabs = response.body().getLabs();
                for (int i = 0; i < alllabs.size(); i++) {
                    mInteractor.setLab(alllabs.get(i));
                }
            }

            @Override
            public void onFailure(Call<LabListPojo> call, Throwable t) {

            }
        });
    }

    @Override
    public void showHospitalsWithDepart(String name) {
        if (allhospitals != null) {
            for (int i = 0; i < allhospitals.size(); i++) {
                ArrayList<String> departments = allhospitals.get(i).getDepartments();
                for (int j = 0; j < departments.size(); j++) {
                    if ((departments.get(j).toString()).equals(name)) {
                        mInteractor.setHospital(allhospitals.get(i));
                    }
                }
            }
        }
    }

    @Override
    public void showLabsWithSpec(String name) {
        if (alllabs != null) {
            for (int i = 0; i < alllabs.size(); i++) {
                ArrayList<String> specializations = alllabs.get(i).getLabSpecializations();
                for (int j = 0; j < specializations.size(); j++) {
                    if ((specializations.get(j).toString()).equals(name)) {
                        mInteractor.setLab(alllabs.get(i));
                    }
                }
            }
        }
    }

    @Override
    public void performSearch(String str) {
        Service ApiSearch = ApiUtils.PerformSearch();
        Log.i("hoda","search apiiiiiiiiiiiiii"+str);
        ApiSearch.performSearch(str).enqueue(new Callback<SearchPojoList>() {
            @Override
            public void onResponse(Call<SearchPojoList> call, Response<SearchPojoList> response) {
              // Log.i("hoda","search api");
                ArrayList<SearchPojo> result = response.body().getResult();
                if(result.size()!=0) {
                    for (int j = 0; j < result.size(); j++) {
                        res = result.get(j);

                       Log.i("hoda", "newwwwwww apinnnn" + res.getMedicaltypr_id()+res.getService_id());
                        if (res.getMedicaltypr_id() == 1) {
                          // Log.i("hoda", "newwwwwww apinnnnnn");
                            HospitalPojo H = getServiceHospital(res.getMedicaltypr_id(), res.getService_id());
                            mInteractor.setSearchRes_Hospital(H);
                            res=null;
                            hotspital=null;
                        }
                        else if (res.getMedicaltypr_id() == 2) {
                            ClinicPojo H = getServiceClinic(res.getMedicaltypr_id(), res.getService_id());
                            mInteractor.setSearchRes_Clinic(H);

                            clinic=null;
                            res=null;
                        }
                        else if (res.getMedicaltypr_id() == 3) {
                    //        Log.i("hoda", "newwwwwww api");
                            PharmacyPojo H =getServicePharmacy(res.getMedicaltypr_id(), res.getService_id());
                            mInteractor.setSearchRes_Pharmay(H);
                            phamacy=null;
                            res=null;
                        }
                        else if (res.getMedicaltypr_id() == 4) {
                     //       Log.i("hoda", "newwwwwww api");
                            LabPojo H = getServiceLab(res.getMedicaltypr_id(), res.getService_id());
                            mInteractor.setSearchRes_Lab(H);
                            lab=null;
                            res=null;
                        }
                    }
                }
                else
                    {
                        mInteractor.setSearchRes_NoResult();
                    }

                }

            @Override
            public void onFailure(Call<SearchPojoList> call, Throwable t) {
                Log.i("hoda","noooooooooo zfttttt");
            }
        });
    }

    @Override
    public HospitalPojo getServiceHospital(int type_id, int medicalservice_id) {
        Service ApiService = ApiUtils.getService();
        Log.i("key", "inside getServiceHospital"+type_id+medicalservice_id);

        ApiService.getServiceHospital(type_id,medicalservice_id).enqueue(new Callback<HospitalPojo>() {
            @Override
            public void onResponse(Call<HospitalPojo> call, Response<HospitalPojo> response) {
                hotspital=response.body();
            }

            @Override
            public void onFailure(Call<HospitalPojo> call, Throwable t) {

            }
        });
        return  hotspital;
    }

    @Override
    public ClinicPojo getServiceClinic(int type_id, int medicalservice_id) {
        Log.i("key", "inside getServiceClinic"+type_id+medicalservice_id);

        Service ApiService = ApiUtils.getService();
        ApiService.getServiceClinic(type_id,medicalservice_id).enqueue(new Callback<ClinicPojo>() {
            @Override
            public void onResponse(Call<ClinicPojo> call, Response<ClinicPojo> response) {
                clinic=response.body();
            }

            @Override
            public void onFailure(Call<ClinicPojo> call, Throwable t) {

            }
        });
        return clinic;
    }

    @Override
    public PharmacyPojo getServicePharmacy(int type_id, int medicalservice_id) {
        Log.i("key", "inside getServicePharmacy"+type_id+medicalservice_id);

        Service ApiService = ApiUtils.getService();
        ApiService.getServicePharmacy(type_id,medicalservice_id).enqueue(new Callback<PharmacyPojo>() {
            @Override
            public void onResponse(Call<PharmacyPojo> call, Response<PharmacyPojo> response) {
                phamacy=response.body();
                Log.i("tryyy",phamacy.getNameEn());
            }

            @Override
            public void onFailure(Call<PharmacyPojo> call, Throwable t) {

            }
        });
        return phamacy;
    }

    @Override
    public LabPojo getServiceLab(int type_id, int medicalservice_id) {
        Log.i("key", "inside getServiceLab"+type_id+medicalservice_id);

        Service ApiService = ApiUtils.getService();
        ApiService.getServiceLab(type_id,medicalservice_id).enqueue(new Callback<LabPojo>() {
            @Override
            public void onResponse(Call<LabPojo> call, Response<LabPojo> response) {
                lab=response.body();
            }

            @Override
            public void onFailure(Call<LabPojo> call, Throwable t) {

            }
        });
        return  lab;
    }

}
