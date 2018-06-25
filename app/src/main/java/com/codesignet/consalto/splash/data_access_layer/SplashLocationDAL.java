package com.codesignet.consalto.splash.data_access_layer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.codesignet.consalto.splash.ui.activity.SplashActivity;
import com.codesignet.consalto.splash.view.SplashInteractorInterface;
import com.codesignet.consalto.splash.view.SplashLocationDALInterface;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Aya on 01/06/2018.
 */

public class SplashLocationDAL implements SplashLocationDALInterface {
    Context context;
    SplashInteractorInterface interactorObject;
    LocationManager loc;
    Geocoder geocoder;
    List<Address> allAddress;
    private double longitudes;
    private double latitudes;
    String locationCity;
    private static final int reqOne = 1;



    public SplashLocationDAL(Context context, SplashInteractorInterface interactorObject) {
        this.interactorObject = interactorObject;
        this.context = context;
    }


    @Override
    public void getLocationInDAL() {
        loc = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        geocoder = new Geocoder(context, Locale.getDefault());
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, reqOne);
            return;
        }
        else{
        loc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitudes = location.getLatitude();
                longitudes = location.getLongitude();
                try {
                    allAddress = geocoder.getFromLocation(latitudes, longitudes, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (allAddress != null) {
                     locationCity = allAddress.get(0).getAdminArea();

                }
                loc.removeUpdates(this);
                returnLocationFromDAL(locationCity);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }

        });
    }

    }


    @Override
    public void returnLocationFromDAL(String location) {
      interactorObject.returnLocationFromInteractor(location);
    }
}
