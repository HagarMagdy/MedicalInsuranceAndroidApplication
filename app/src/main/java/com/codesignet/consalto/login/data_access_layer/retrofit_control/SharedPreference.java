package com.codesignet.consalto.login.data_access_layer.retrofit_control;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;


import com.codesignet.consalto.R;
import com.codesignet.consalto.login.view.SharedPreferencesInterface;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Hagar on 31/05/2018.
 */

public class SharedPreference implements SharedPreferencesInterface {


    @Override
    public void saveToSharedPreferences(String mail, String password, Context context,int id) {
        String userNameData = mail;
        String passwordData = password;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("username", userNameData);
        edit.putString("password", passwordData);
        Log.i("empId","employee id is"+id);
        edit.putInt("id",id);
        edit.putBoolean("logFlag",true);
        edit.commit();
        Toast.makeText(context, context.getResources().getString(R.string.saved_to_sp), Toast.LENGTH_SHORT).show();

    }
}
