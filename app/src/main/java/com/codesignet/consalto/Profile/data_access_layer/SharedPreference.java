package com.codesignet.consalto.Profile.data_access_layer;

import android.content.Context;

import com.codesignet.consalto.Profile.view.ProfileInteractorInterface;


/**
 * Created by Aya on 5/31/2018.
 */

public class SharedPreference {

    private Context context;
    private ProfileInteractorInterface interactor;

    public SharedPreference(Context context, ProfileInteractorInterface interactor) {
        this.context = context;
        this.interactor = interactor;
    }


}
