package com.codesignet.consalto.splash.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codesignet.consalto.R;
import com.codesignet.consalto.login.ui.activity.LoginActivity;
import com.codesignet.consalto.splash.data_access_layer.SplashInteractor;
import com.codesignet.consalto.splash.presenter.SplashPresenter;
import com.codesignet.consalto.splash.view.SplashPresenterInterface;
import com.codesignet.consalto.splash.view.SplashSharedPrefrenceInterface;
import com.codesignet.consalto.splash.view.SplashViewInterface;

import java.io.IOException;
import java.util.List;

import me.kartikarora.potato.Potato;

public class SplashActivity extends AppCompatActivity  implements SplashViewInterface {

    SplashPresenterInterface presenterObject;
    boolean loginFlag;
    boolean internetConnectionFlag;
    boolean GPSConnectionFlag;
    private static final int reqLocation=1;
    String location;

    LocationManager loc;
    Geocoder geocoder;
    List<Address> allAddress;
    private double longitudes;
    private double latitudes;
    String locationCity;
    private static final int reqOne = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterObject = new SplashPresenter(this, getApplicationContext());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                internetConnectionFlag = Potato.potate(getApplicationContext()).Utils().isInternetConnected();
                if (internetConnectionFlag == true) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, reqLocation);
                    return;
                } else {
                    getLocation();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            Toast.makeText(SplashActivity.this, "city is is is is " + location, Toast.LENGTH_LONG).show();
                        }
                    }, 5000);
                }
                    GPSConnectionFlag = Potato.potate(getApplicationContext()).Utils().isGPSEnabled(getApplicationContext());
                    checkInSplashActivity();
                    if (loginFlag) {

                        Intent loginTrue = new Intent(SplashActivity.this, com.codesignet.consalto.home.Home.activity.HomeActivity
                                .class);
                        startActivity(loginTrue);


                    } else {
                        Intent loginFalse = new Intent(SplashActivity.this, LoginActivity
                                .class);
                        startActivity(loginFalse);
                    }
                }

                else {
                    Toast.makeText(SplashActivity.this,getResources().getString(R.string.noInternet), Toast.LENGTH_LONG).show();
                }
                finish();
            }
        }, 1000);
    }

    @Override
    public void checkInSplashActivity() {
        presenterObject.checkInSplashPresenter();
    }

    @Override
    public void returnFlagsFromView( boolean loginFlag) {
        this.loginFlag=loginFlag;
    }

    public void getLocation(){
        presenterObject.getLocationInPresenter();
    }


    public void returnLocationFromView(String location){
        this.location = location;
    }
}
