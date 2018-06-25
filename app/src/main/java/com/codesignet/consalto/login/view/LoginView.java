package com.codesignet.consalto.login.view;

/**
 * Created by Hagar on 30/05/2018.
 */

public interface LoginView {
    void navigateToHomeScreen(int id);
    void loginError();
    boolean isValidEmail(String email);
    void emailValidationFailed();
    void showError();
    void successLogin();
    void userLoginError();

}
