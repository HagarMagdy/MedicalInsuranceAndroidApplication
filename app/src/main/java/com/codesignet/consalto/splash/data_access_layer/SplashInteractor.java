package com.codesignet.consalto.splash.data_access_layer;

import android.content.Context;
import android.util.Log;

import com.codesignet.consalto.splash.view.SplashInteractorInterface;
import com.codesignet.consalto.splash.view.SplashPresenterInterface;
import com.codesignet.consalto.splash.view.SplashSharedPrefrenceInterface;

/**
 * Created by Aya on 01/06/2018.
 */

public class SplashInteractor implements SplashInteractorInterface {

    SplashPresenterInterface presenterObject;
    SplashSharedPrefrenceInterface sharedPrefrenceObject;
    SplashLocationDAL locationDalObject;
    Context context;
    public SplashInteractor( SplashPresenterInterface presenterObject ,Context context) {
        this.presenterObject= presenterObject;
        this.context=context;
        this.sharedPrefrenceObject=new SplashSharedPrefrence(context,this);
        this.locationDalObject=new SplashLocationDAL(context,this);
    }

    @Override
    public void checkInSplashInteractor()
    {
        Log.i("success","in interactor");

        sharedPrefrenceObject.checkInSplashSharedPrefrence();
    }

    @Override
    public void returnFlagsFromInteractor( boolean loginFlag) {
        presenterObject.returnFlagsFromPresenter(loginFlag);
    }

    public void getLocationInInteractor(){
        locationDalObject.getLocationInDAL();
    }

    public void returnLocationFromInteractor(String location){
        presenterObject.returnLocationFromPresenter(location);
    }

}
