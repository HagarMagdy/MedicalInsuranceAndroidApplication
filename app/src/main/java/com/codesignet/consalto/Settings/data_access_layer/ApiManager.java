package com.codesignet.consalto.Settings.data_access_layer;

import android.content.Context;

import com.codesignet.consalto.Settings.view.SettingsInteractorInterface;


/**
 * Created by Aya on 5/31/2018.
 */

public class ApiManager {

    private Context context;
    private SettingsInteractorInterface interactor;

    public ApiManager(Context context,SettingsInteractorInterface interactor) {
        this.context=context;
        this.interactor=interactor;
    }
}
