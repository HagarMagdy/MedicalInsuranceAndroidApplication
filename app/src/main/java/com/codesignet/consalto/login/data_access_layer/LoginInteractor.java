package com.codesignet.consalto.login.data_access_layer;

import android.content.Context;
import android.util.Log;

import com.codesignet.consalto.login.data_access_layer.retrofit_control.LoginApiManager;
import com.codesignet.consalto.login.view.LoginApiManagerInterface;
import com.codesignet.consalto.login.view.LoginInteractorInterface;
import com.codesignet.consalto.login.view.LoginPresenterInterface;

/**
 * Created by Hagar on 30/05/2018.
 */

public class LoginInteractor implements LoginInteractorInterface {
    LoginPresenterInterface presenter;
    LoginApiManagerInterface apiManager;

    public LoginInteractor(LoginPresenterInterface presenter) {
        this.presenter = presenter;
        initLoginApiManager();
    }
    void initLoginApiManager() {
        apiManager = new LoginApiManager(this);
    }

        @Override
    public void passId(int id) {
      presenter.sendIdToMain(id);
    }

    @Override
    public void login(String mail, String password,Context context) {
        Log.i("KEY"," Interactor/login");
        apiManager.ProsessLogin(mail,password,context);
    }

    @Override
    public void retrofitError() {
        presenter.loginError();
    }

    @Override
    public void success() {
        presenter.successUserLogin();
    }

    @Override
    public void userNotFound() {
    presenter.erroeEmailOrPassword();
    }
}
