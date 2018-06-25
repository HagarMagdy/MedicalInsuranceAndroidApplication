package com.codesignet.consalto.home.Home.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.codesignet.consalto.R;
import com.codesignet.consalto.Settings.LocaleHelper;
import com.codesignet.consalto.Settings.ui.SettingsActivity;
import com.codesignet.consalto.Settings.view.SettingsInteractorInterface;
import com.codesignet.consalto.animateFragment.fragmentsView.animatedFragmentdown;
import com.codesignet.consalto.animateFragment.fragmentsView.animatedFragmentup;
import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;
import com.codesignet.consalto.home.Home.presenter.HomePresenter;
import com.codesignet.consalto.home.Home.view.HomePresenter_Interface;
import com.codesignet.consalto.home.Home.view.HomeView_Interface;
import com.codesignet.consalto.medical_service_details.ui.activity.DetailsActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.codesignet.consalto.login.data_access_layer.retrofit_control.RetrofitClient.context;

public class HomeActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, com.codesignet.consalto.home.Home.view.HomeView_Interface {
    boolean appearFlag=false;
    @BindView(R.id.service_filter)
    Button serviceFilter;
    @BindView(R.id.department_filter)
    Button departmentFilter;
    @BindView(R.id.spinnerlayout)
    LinearLayout spinnerlayout;
    @BindView(R.id.spinner1)
    ListView spinner1;
    @BindView(R.id.spinner2)
    ListView spinner2;
    @BindView(R.id.spinner2layout)
    LinearLayout spinner2layout;
    @BindView(R.id.menue)
    Button menue;
    @BindView(R.id.spinner3)
    ListView spinner3;
    @BindView(R.id.spinner3layout)
    LinearLayout spinner3layout;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.laout)
    FrameLayout laout;
    private GoogleMap mMap;
    private HomePresenter_Interface presenter;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    int serviceflag;
    String name_department;
    ArrayList<String> search_arry;
    List<Pair<Integer, Object>> search_obj;
    HashMap<Object, Integer> marker_obj;
    boolean[] choices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        presenter = new HomePresenter(this);
        choices = new boolean[4];
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        choices[0] = sharedPreferences.getBoolean("Hospital", true);
        choices[1] = sharedPreferences.getBoolean("Pharmacy", true);
        choices[2] = sharedPreferences.getBoolean("Lab", true);
        choices[3] = sharedPreferences.getBoolean("Clinic", true);
        search.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(17)});
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences prefs = newBase.getSharedPreferences("Settings", SettingsActivity.MODE_PRIVATE);
        String language = prefs.getString("Language", "");
        super.attachBaseContext(LocaleHelper.wrap(newBase, language));
    }

    @Override
    public void initView() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        search_arry = new ArrayList<String>();
        search_obj = new LinkedList<>();
        marker_obj = new HashMap<>();
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("character", s.toString());
                if (count != 0) {
                    search_arry.clear();
                    search_obj.clear();
                    spinner3.deferNotifyDataSetChanged();
                    spinner3layout.setVisibility(View.INVISIBLE);
                    spinner3.setVisibility(View.INVISIBLE);
                    presenter.performSearch(s.toString());
                } else {
                    search_arry.clear();
                    search_obj.clear();
                    spinner3.deferNotifyDataSetChanged();
                    spinner3layout.setVisibility(View.INVISIBLE);
                    spinner3.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                spinner3.deferNotifyDataSetChanged();
                spinner3layout.setVisibility(View.INVISIBLE);
                spinner3.setVisibility(View.INVISIBLE);
            }
        });
        Log.i("hoda", "view");
        spinner3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("details", (Serializable) search_obj.get(position).second);
                intent.putExtra("type", search_obj.get(position).first);
                startActivity(intent);

            }
        });
    }

    @Override
    public void setHospitalLocation(HospitalPojo hospital) {
        Log.i("key","setHospitalLocation");

        LatLng latLng = new LatLng(hospital.getLatitude(), hospital.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(hospital.getNameEn());
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.h1);
        markerOptions.icon(icon);
        Marker mark = mMap.addMarker(markerOptions);
        mark.setTag(hospital);
        mark.showInfoWindow();
        marker_obj.put(mark.getTag(), 1);
    }

    @Override
    public void setPharamacyLocation(PharmacyPojo pharmacy) {
        Log.i("key","setPharamacyLocation");

        LatLng latLng = new LatLng(pharmacy.getLatitude(), pharmacy.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(pharmacy.getNameEn());
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.p1);
        markerOptions.icon(icon);
        Marker mark = mMap.addMarker(markerOptions);
        mark.setTag(pharmacy);
        mark.showInfoWindow();
        marker_obj.put(mark.getTag(), 2);
    }

    @Override
    public void setClinicLocation(ClinicPojo clinic) {
        Log.i("key","setClinicLocation");

        LatLng latLng = new LatLng(clinic.getLatitude(), clinic.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(clinic.getDoctorNameEn());
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.c1);
        markerOptions.icon(icon);
        Marker mark = mMap.addMarker(markerOptions);
        mark.setTag(clinic);
        mark.showInfoWindow();
        marker_obj.put(mark.getTag(), 3);
    }

    @Override
    public void setLabLocation(LabPojo lab) {
        LatLng latLng = new LatLng(lab.getLatitude(), lab.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(lab.getNameEn());
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.l1);
        markerOptions.icon(icon);
        Marker mark = mMap.addMarker(markerOptions);
        mark.setTag(lab);
        mark.showInfoWindow();
        marker_obj.put(mark.getTag(), 4);
    }

    @Override
    public void setSearchHospital(HospitalPojo result) {
        Log.i("keyyyyyyyy", "jjjjj");
        if (result != null) {
            Pair h = new Pair(1, result);
            search_obj.add(h);
            search_arry.add(result.getNameEn());
            Log.i("serrrrrrrrrr", result.getNameEn());
            spinner3.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, search_arry));
            spinner3.deferNotifyDataSetChanged();
            spinner3layout.setVisibility(View.VISIBLE);
            spinner3.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void setSearchPharmacy(PharmacyPojo result) {
        Log.i("keyyyyyyyy", "jjjjj");
        if (result != null) {
            search_arry.add(result.getNameEn());
            Pair p = new Pair(2, result);
            search_obj.add(p);
            Log.i("serrrrrrrrrr", result.getNameEn());
            spinner3.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, search_arry));
            spinner3.deferNotifyDataSetChanged();
            spinner3layout.setVisibility(View.VISIBLE);
            spinner3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setSearchClinic(ClinicPojo result) {
        Log.i("keyyyyyyyy", "jjjjj");
        if (result != null) {
            search_arry.add(result.getDoctorNameEn());
            Pair c = new Pair(3, result);
            search_obj.add(c);
            Log.i("serrrrrrrrrr", result.getDoctorNameEn());
            spinner3.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, search_arry));
            spinner3.deferNotifyDataSetChanged();
            spinner3layout.setVisibility(View.VISIBLE);
            spinner3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setSearchLab(LabPojo result) {
        Log.i("keyyyyyyyy", "jjjjj");
        if (result != null) {
            search_arry.add(result.getNameEn());
            Pair l = new Pair(4, result);
            search_obj.add(l);
            Log.i("serrrrrrrrrr", result.getNameEn());
            spinner3.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, search_arry));
            spinner3.deferNotifyDataSetChanged();
            spinner3layout.setVisibility(View.VISIBLE);
            spinner3.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void setSearchRes_NoResult() {
        search_arry.clear();
        search_arry.add("No Result");
        spinner3.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, search_arry));
        spinner3.deferNotifyDataSetChanged();
        spinner3layout.setVisibility(View.VISIBLE);
        spinner3.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getIntent().removeExtra("type");
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                if (!marker.getTitle().equals("My Location")) {
                    Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                    getIntent().removeExtra("type");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("details", (Serializable) marker.getTag());
                    intent.putExtra("type", marker_obj.get(marker.getTag()));
                    Log.i("marker", "" + intent.getIntExtra("type", 0));
                    startActivity(intent);
                }
                return true;
            }
        });

        if (choices[0]==true)
        {
           mMap.clear();
            presenter.getHospital();
        }
        if(choices[1]==true)
        {
            mMap.clear();
            presenter.getPharmacy();
        }
        if (choices[2]==true)
        {
            mMap.clear();
            presenter.getLab();
        }
        if (choices[3]==true)
        {
            mMap.clear();
            presenter.getClinic();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        if(appearFlag==true)
        {
            Log.i("key","true d5l");
//           animatedFragmentdown downObject = new animatedFragmentdown();
//           downObject.removeFragmentDown();
//            animatedFragmentup upObject = new animatedFragmentup();
////            upObject.removeFragmentUp();
            FragmentManager fm =this
                    .getSupportFragmentManager();
            fm.popBackStack ("downFrag", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fm.popBackStack ("upFrag", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            appearFlag=false;

        }
        else {
            Log.i("key","d5l false");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
        }
    }



    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("My Location");
        mCurrLocationMarker = mMap.addMarker(markerOptions);
        //move map camera
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(HomeActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @OnClick(R.id.service_filter)
    public void onServiceFilterClicked() {
        spinnerlayout.setVisibility(View.VISIBLE);
        spinner1.setVisibility(View.VISIBLE);
        spinner2.setVisibility(View.INVISIBLE);
        spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mMap.clear();
                    presenter.getAllServices();
                    spinner1.setVisibility(View.INVISIBLE);
                    departmentFilter.setEnabled(false);

                }
                if (position == 1) {
                    mMap.clear();
                    presenter.getHospital();
                    spinner1.setVisibility(View.INVISIBLE);
                    departmentFilter.setEnabled(true);
                    serviceflag = 1;
                }
                if (position == 2) {
                    mMap.clear();
                    presenter.getPharmacy();
                    spinner1.setVisibility(View.INVISIBLE);
                    departmentFilter.setEnabled(false);
                }
                if (position == 3) {
                    mMap.clear();
                    presenter.getClinic();
                    spinner1.setVisibility(View.INVISIBLE);
                    departmentFilter.setEnabled(false);
                }
                if (position == 4) {
                    mMap.clear();
                    presenter.getLab();
                    spinner1.setVisibility(View.INVISIBLE);
                    departmentFilter.setEnabled(true);
                    serviceflag = 2;
                }
            }
        });
    }

    @OnClick(R.id.department_filter)
    public void onDepartmentFilterClicked() {
        spinner1.setVisibility(View.INVISIBLE);
        if (serviceflag == 1) {
            String[] hospital_departments = getResources().getStringArray(R.array.Hospital_Departments);
            spinner2.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, hospital_departments));
            spinner2layout.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
        }
        if (serviceflag == 2) {
            String[] lab_specialization = getResources().getStringArray(R.array.Lab_Specialization);
            spinner2.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, lab_specialization));
            spinner2layout.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
        }
        spinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name_department = parent.getItemAtPosition(position).toString();
                spinner2.setVisibility(View.INVISIBLE);
                if (serviceflag == 1) {
                    mMap.clear();
                    presenter.showHospitalsWithDepart(name_department);
                }
                if (serviceflag == 2) {
                    mMap.clear();
                    presenter.showLabsWithSpec(name_department);
                }
            }
        });
    }

    @OnClick(R.id.menue)
    public void onViewClicked() {
        if(appearFlag==false) {
            this.findViewById(R.id.menue).setVisibility(View.VISIBLE);
            animatedFragmentup upFragmentObj = new animatedFragmentup();
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
            transaction.add(R.id.layoutFragUp, upFragmentObj, "upFragmentLoad");
            transaction.addToBackStack("upFrag");
            transaction.commit();
            animatedFragmentdown downFragmentObj = new animatedFragmentdown();
            FragmentManager fragmentManager2 = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
            transaction2.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
            transaction2.add(R.id.layoutFragDown, downFragmentObj, "downFragmentLoad");
            transaction2.addToBackStack("downFrag");
            transaction2.commit();
            appearFlag=true;
        }
        else{
            this.findViewById(R.id.menue).setVisibility(View.VISIBLE);
            animatedFragmentup upFragmentObj=new animatedFragmentup();
            FragmentManager fragmentManager= getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_left);
            transaction.remove(getSupportFragmentManager().findFragmentByTag("upFragmentLoad"));
            transaction.commit();
            animatedFragmentdown downFragmentObj=new animatedFragmentdown();
            FragmentManager fragmentManager2= getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
            transaction2.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_right);
            transaction2.remove(getSupportFragmentManager().findFragmentByTag("downFragmentLoad"));
            transaction2.commit();
            appearFlag=false;
        }
    }
}
