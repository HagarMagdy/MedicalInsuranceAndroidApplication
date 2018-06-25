package com.codesignet.consalto.login.view;

import android.content.Context;

/**
 * Created by Hagar on 31/05/2018.
 */

public interface SharedPreferencesInterface {
    void saveToSharedPreferences(String mail,String password,Context context,int id);
}
