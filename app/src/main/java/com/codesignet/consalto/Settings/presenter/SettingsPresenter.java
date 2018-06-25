package com.codesignet.consalto.Settings.presenter;


import android.content.Context;

import com.codesignet.consalto.Settings.data_access_layer.SettingsInteractor;
import com.codesignet.consalto.Settings.view.SettingsInteractorInterface;
import com.codesignet.consalto.Settings.view.SettingsPresenterInterface;
import com.codesignet.consalto.Settings.view.SettingsViewInterface;


/**
 * Created by Aya on 5/31/2018.
 */

public class SettingsPresenter implements SettingsPresenterInterface {

    private SettingsInteractorInterface Interactor;
    private SettingsViewInterface View;
    private SettingsPresenterInterface Presenter;
    private Context context;

    public SettingsPresenter(SettingsViewInterface View, Context context) {
        Interactor = new SettingsInteractor(this, context);
        this.View = View;
        this.context = context;
    }

    public void saveMedicalTypesChoices(boolean[] choices) {
        Interactor.saveMedicalTypesChoices(choices);
    }

    public void getChoices() {
        Interactor.getChoices();
    }

    public void getChoices(boolean[] choices) {
        View.getChoices(choices);
    }

    public void setLocale(String language, Context context) {
        Interactor.setLocale(language, context);
    }

    public void getLocale() {
        Interactor.getLocale();
    }

    public void getLocaleFromShared(String language) {
        View.getLocaleFromShared(language);
    }

    public void logout(Context context)
    {
        Interactor.logout(context);
    }
}
