package com.codesignet.consalto.Settings.view;

import android.content.Context;

/**
 * Created by Aya on 5/31/2018.
 */

public interface SettingsPresenterInterface {
    public void saveMedicalTypesChoices(boolean[] choices);

    public void getChoices();

    public void getChoices(boolean[] choices);

    public void setLocale(String language, Context context);

    public void getLocale();

    public void getLocaleFromShared(String language);

    public void logout(Context context)
            ;

}
