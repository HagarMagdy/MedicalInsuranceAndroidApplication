package com.codesignet.consalto.splash.data_access_layer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.codesignet.consalto.login.data_access_layer.retrofit_control.SharedPreference;
import com.codesignet.consalto.splash.view.SplashInteractorInterface;
import com.codesignet.consalto.splash.view.SplashSharedPrefrenceInterface;

import me.kartikarora.potato.Potato;

/**
 * Created by Aya on 31/05/2018.
 */

public class SplashSharedPrefrence implements SplashSharedPrefrenceInterface {
    Context context;
    SplashInteractorInterface interactorObject;
    private static final int reqOne = 1;


    public SplashSharedPrefrence(Context context, SplashInteractorInterface interactorObject) {
        this.interactorObject = interactorObject;
        this.context = context;
    }

    @Override
    public void checkInSplashSharedPrefrence() {
        SharedPreferences sharedPrefrence = PreferenceManager.getDefaultSharedPreferences(context);
       boolean existFlag = sharedPrefrence.getBoolean("logFlag",false);
        returnFlagsFromSharedPrefrence(existFlag);
    }

    @Override
    public void returnFlagsFromSharedPrefrence( boolean loginFlag) {
        interactorObject.returnFlagsFromInteractor(loginFlag);
    }




}
