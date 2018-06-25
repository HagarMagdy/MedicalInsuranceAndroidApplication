package com.codesignet.consalto.home.Home.Pojos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hoda.CO on 02/06/2018.
 */

public class SearchPojo {
    @SerializedName("id")
    int service_id ;
    @SerializedName("typeId")
    int medicaltypr_id;

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getMedicaltypr_id() {
        return medicaltypr_id;
    }

    public void setMedicaltypr_id(int medicaltypr_id) {
        this.medicaltypr_id = medicaltypr_id;
    }
}
