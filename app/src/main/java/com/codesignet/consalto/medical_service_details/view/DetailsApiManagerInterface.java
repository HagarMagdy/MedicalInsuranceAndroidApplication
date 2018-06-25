package com.codesignet.consalto.medical_service_details.view;

/**
 * Created by Hagar on 31/05/2018.
 */

public interface DetailsApiManagerInterface {
    void retrieveDetails(int tid, int sid);
    void retrievePharmacyDetails(int tid, int sid);
    void retrieveLabDetails(int tid, int sid);
    void retrieveClinicDetails(int tid, int sid);

}
