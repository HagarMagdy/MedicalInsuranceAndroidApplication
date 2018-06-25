package com.codesignet.consalto.Profile.presenter;


import android.content.Context;

import com.codesignet.consalto.Profile.data_access_layer.ProfileInteractor;
import com.codesignet.consalto.Profile.retrofit_control.Pojos.Employee;
import com.codesignet.consalto.Profile.view.ProfileInteractorInterface;
import com.codesignet.consalto.Profile.view.ProfilePresenterInterface;
import com.codesignet.consalto.Profile.view.ProfileViewInterface;


/**
 * Created by Aya on 5/31/2018.
 */

public class ProfilePresenter implements ProfilePresenterInterface {

    private ProfileInteractorInterface Interactor;
    private ProfileViewInterface View;
    private Context context;

    public ProfilePresenter(ProfileViewInterface View, Context context) {
        Interactor = new ProfileInteractor(this, context);
        this.View = View;
        this.context = context;
    }

    public void passEmployeeID(int id)

    {
        Interactor.passEmployeeID(id);
    }

    public void getEmployee(Employee employee) {
        View.getEmployee(employee);
    }


}
