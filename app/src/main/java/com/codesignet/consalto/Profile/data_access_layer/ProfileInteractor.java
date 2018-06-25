package com.codesignet.consalto.Profile.data_access_layer;

import android.content.Context;

import com.codesignet.consalto.Profile.retrofit_control.Pojos.Employee;
import com.codesignet.consalto.Profile.view.ProfileInteractorInterface;
import com.codesignet.consalto.Profile.view.ProfilePresenterInterface;


/**
 * Created by Aya on 5/31/2018.
 */

public class ProfileInteractor implements ProfileInteractorInterface {

    private ProfilePresenterInterface presenter;
    private SharedPreference sharedPreference;
    private ApiManager apiManager;
    private Context context;

    public ProfileInteractor(ProfilePresenterInterface presenter, Context context) {
        this.presenter = presenter;
        this.sharedPreference = new SharedPreference(context, this);
        this.apiManager = new ApiManager(context, this);
    }


    public void passEmployeeID(int id) {
        apiManager.getEmployeeData(id);

    }

    public void getEmployee(Employee employee) {
        presenter.getEmployee(employee);

    }
}
