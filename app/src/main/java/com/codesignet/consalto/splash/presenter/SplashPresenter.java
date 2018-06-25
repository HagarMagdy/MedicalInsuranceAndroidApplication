package com.codesignet.consalto.splash.presenter;

import android.content.Context;
import android.util.Log;


import com.codesignet.consalto.splash.data_access_layer.SplashInteractor;
import com.codesignet.consalto.splash.view.SplashInteractorInterface;
import com.codesignet.consalto.splash.view.SplashPresenterInterface;
import com.codesignet.consalto.splash.view.SplashViewInterface;

/**
 * Created by Aya on 31/05/2018.
 */

public class SplashPresenter implements SplashPresenterInterface {

    SplashViewInterface viewObject;
    SplashInteractorInterface interactorObject;
    Context context;

    public SplashPresenter(SplashViewInterface viewObject,Context context) {
        this.viewObject = viewObject;
        this.interactorObject=new SplashInteractor(this,context);
        this.context=context;
    }

    @Override
    public void checkInSplashPresenter() {
        Log.i("success","in presenter");
        interactorObject.checkInSplashInteractor();
    }

    @Override
    public void returnFlagsFromPresenter(boolean loginFlag) {
        viewObject.returnFlagsFromView(loginFlag);
    }

    public void getLocationInPresenter(){
        interactorObject.getLocationInInteractor();
    }

    public void returnLocationFromPresenter(String location){
        viewObject.returnLocationFromView(location);
    }
}
