package com.codesignet.consalto.feedback.data_access_layer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.codesignet.consalto.feedback.view.FeedbackInteractorInterface;
import com.codesignet.consalto.feedback.view.FeedbackSharedPrefrenceInterface;

import me.kartikarora.potato.Potato;

/**
 * Created by Aya on 30/05/2018.
 */

public class FeedbackSharedPrefrence implements FeedbackSharedPrefrenceInterface {
    Context context;
    FeedbackInteractorInterface interactor;
    public FeedbackSharedPrefrence(Context context,FeedbackInteractorInterface interactor) {
        this.interactor = interactor;
        this.context=context;
    }

    @Override
    public void saveSharedPrefrenceRateFlag(boolean rateFlag,int reviewId)
    {
        Log.i("keyFLag"," review id  in save shared prefrence"+ reviewId);
       // Potato.potate(context).Preferences().putSharedPreference("rateFlag",rateFlag);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("rateFlag",rateFlag);
        editor.putInt("reviewId",reviewId);
        editor.commit();
    }

    @Override
    public void getSharedPrefrenceRateFlag() {
        boolean rateFlag=Potato.potate(context).Preferences().getSharedPreferenceBoolean("rateFlag");
        interactor.getSharedPrefrence(rateFlag);
    }

    @Override
    public void isContain() {
//        boolean existFlag = Potato.potate(context).Preferences().sharedPreferenceExists("rateFlag");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean existFlag = sharedPreferences.getBoolean("rateFlag", false);
        int reviewId = sharedPreferences.getInt("reviewId",0);
        Log.i("keyFLag"," keyFlag in isContain"+ reviewId);
        interactor.existKey(existFlag,reviewId);
    }


}
