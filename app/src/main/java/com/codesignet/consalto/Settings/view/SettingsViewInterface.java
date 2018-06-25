package com.codesignet.consalto.Settings.view;

import android.view.View;

/**
 * Created by Aya on 5/31/2018.
 */

public interface SettingsViewInterface {

    public void save(View view);

    public void getChoices();
    public void getChoices(boolean[] choices);

    public void getLocaleFromShared(String language);

    public void logout(View view);


}
