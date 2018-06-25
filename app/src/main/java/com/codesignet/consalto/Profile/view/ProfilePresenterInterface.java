package com.codesignet.consalto.Profile.view;


import com.codesignet.consalto.Profile.retrofit_control.Pojos.Employee;

/**
 * Created by Aya on 5/31/2018.
 */

public interface ProfilePresenterInterface {
    public void passEmployeeID(int id);

    public void getEmployee(Employee employee);

}
