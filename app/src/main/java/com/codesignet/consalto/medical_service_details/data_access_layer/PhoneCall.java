package com.codesignet.consalto.medical_service_details.data_access_layer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.codesignet.consalto.R;
import com.codesignet.consalto.medical_service_details.ui.activity.DetailsActivity;
import com.codesignet.consalto.medical_service_details.view.DetailsInteractorInterface;
import com.codesignet.consalto.medical_service_details.view.PhoneCallInterface;

/**
 * Created by Hagar on 03/06/2018.
// */
//
public class PhoneCall implements PhoneCallInterface {
    DetailsInteractorInterface interactor;

    public PhoneCall(DetailsInteractorInterface interactor) {
        this.interactor = interactor;
    }
    @Override
    public void askForPermission(String permission, Integer requestCode, Context context) {

        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((DetailsActivity) context, permission)) {
                ActivityCompat.requestPermissions((DetailsActivity) context, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions((DetailsActivity) context, new String[]{permission}, requestCode);
            }
        } else {
           interactor.launchPhoneCallIntent();
        }
    }
}
