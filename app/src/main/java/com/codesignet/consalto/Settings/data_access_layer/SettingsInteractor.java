package com.codesignet.consalto.Settings.data_access_layer;

import android.content.Context;

import com.codesignet.consalto.Settings.view.SettingsInteractorInterface;
import com.codesignet.consalto.Settings.view.SettingsPresenterInterface;


/**
 * Created by Aya on 5/31/2018.
 */

public class SettingsInteractor implements SettingsInteractorInterface {


    private SettingsPresenterInterface presenter;
    private SharedPreference sharedPreference;
    private Context context;

    public SettingsInteractor(SettingsPresenterInterface presenter, Context context) {
        this.presenter = presenter;
        this.sharedPreference = new SharedPreference(context,this);
    }


    public void saveMedicalTypesChoices(boolean []choices){

        sharedPreference.saveMedicalTypesChoices(choices);
    }

    public void getChoices()
    {
        sharedPreference.getChoices();
    }

    public void getChoices(boolean []choices)
    {
        presenter.getChoices(choices);
    }

    public void setLocale(String language,Context context)
    {
        sharedPreference.setLocale(language,context);
    }

    public void getLocale()
    {
        sharedPreference.getLocale();
    }

    public void getLocaleFromShared(String language)
    {
        presenter.getLocaleFromShared(language);
    }

    public void logout(Context context)
    {

        sharedPreference.clearSharedPreference(context);
    }
}
