package com.codesignet.consalto.login.presenter;

import android.content.Context;
import android.util.Log;

import com.codesignet.consalto.login.data_access_layer.LoginInteractor;
import com.codesignet.consalto.login.view.LoginApiManagerInterface;
import com.codesignet.consalto.login.view.LoginInteractorInterface;
import com.codesignet.consalto.login.view.LoginPresenterInterface;
import com.codesignet.consalto.login.view.LoginView;

/**
 * Created by Hagar on 30/05/2018.
 */

public class LoginPresenter implements LoginPresenterInterface {
    LoginView view;
    LoginInteractorInterface interactor;

    public LoginPresenter(LoginView view) {
        this.view = view;
        initLoginInteractor();

    }
    void initLoginInteractor(){

        interactor=new LoginInteractor(this);
    }
    @Override
    public void sendIdToMain(int id) {
     view.navigateToHomeScreen(id);
    }

    @Override
    public void loginUser(String mail, String password,Context context) {
        interactor.login(mail,password,context);
    }

    @Override
    public void loginError() {
     view.showError();
    }

    @Override
    public void successUserLogin() {
        view.successLogin();
    }

    @Override
    public void erroeEmailOrPassword() {
      view.userLoginError();
    }
}
