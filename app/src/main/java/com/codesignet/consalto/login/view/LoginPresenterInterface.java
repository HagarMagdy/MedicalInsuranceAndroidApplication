package com.codesignet.consalto.login.view;

import android.content.Context;

/**
 * Created by Hagar on 30/05/2018.
 */

public interface LoginPresenterInterface {
    void sendIdToMain(int id);
    void loginUser(String mail,String password,Context context);
    void loginError();
    void successUserLogin();
    void erroeEmailOrPassword();

}
