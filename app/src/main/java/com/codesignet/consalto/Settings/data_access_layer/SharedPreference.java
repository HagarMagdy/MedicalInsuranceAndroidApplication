package com.codesignet.consalto.Settings.data_access_layer;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;


import com.codesignet.consalto.Settings.ui.SettingsActivity;
import com.codesignet.consalto.Settings.view.SettingsInteractorInterface;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Aya on 5/31/2018.
 */

public class SharedPreference {


    private Context context;
    private SettingsInteractorInterface interactor;

    public SharedPreference(Context context, SettingsInteractorInterface interactor) {
        this.context = context;
        this.interactor = interactor;
    }

    public void saveMedicalTypesChoices(boolean[] choices) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Hospital", choices[0]);
        editor.putBoolean("Pharmacy", choices[1]);
        editor.putBoolean("Lab", choices[2]);
        editor.putBoolean("Clinic", choices[3]);
        editor.commit();
    }

    public void getChoices() {
        boolean[] choices = new boolean[4];
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        choices[0] = sharedPreferences.getBoolean("Hospital", true);
        choices[1] = sharedPreferences.getBoolean("Pharmacy", true);
        choices[2] = sharedPreferences.getBoolean("Lab", true);
        choices[3] = sharedPreferences.getBoolean("Clinic", true);
        interactor.getChoices(choices);
    }

    public void setLocale(String language, Context context) {
        Locale locale = new Locale(language);
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = context.getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("Language", language);
        editor.apply();

    }

    public void getLocale() {
        SharedPreferences prefs = context.getSharedPreferences("Settings", SettingsActivity.MODE_PRIVATE);
        String language = prefs.getString("Language", "");

        if(language.equals(""))
        {
            language="en";
        }
        setLocale(language, context);
        interactor.getLocaleFromShared(language);
    }

    public void clearSharedPreference(Context context)
    {
        SharedPreferences preferences =PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor2 = context.getSharedPreferences("Settings", MODE_PRIVATE).edit();
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor2.clear();
        editor.commit();
        editor2.apply();
        getLocale();
        // finish();

    }
}
