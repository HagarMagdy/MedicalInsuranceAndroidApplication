package com.codesignet.consalto.login.view;

import android.content.Context;

/**
 * Created by Hagar on 30/05/2018.
 */

public interface LoginInteractorInterface {
    void passId(int id);
    void login(String mail,String password,Context context);
    void retrofitError();
    void success();
    void userNotFound();

}
