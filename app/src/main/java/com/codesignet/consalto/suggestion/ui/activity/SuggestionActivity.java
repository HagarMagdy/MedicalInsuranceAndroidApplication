package com.codesignet.consalto.suggestion.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codesignet.consalto.R;
import com.codesignet.consalto.feedback.ui.activity.FeedbackActivity;
import com.codesignet.consalto.home.Home.activity.HomeActivity;
import com.codesignet.consalto.suggestion.pojo.Suggestion;
import com.codesignet.consalto.suggestion.presenter.SuggestionPresenter;
import com.codesignet.consalto.suggestion.view.SuggestionPresenterInterface;
import com.codesignet.consalto.suggestion.view.SuggestionViewInterface;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuggestionActivity extends Activity implements SuggestionViewInterface{
    @BindView(R.id.suggestionenteredSuggestion)
    EditText enterdSuggestion;
    @BindView(R.id.suggestionspinnerMedicalType)
    Spinner medicalServiceTypeSpinner;
    @BindView(R.id.suggestionaddress)
    EditText suggestionAddress ;
    @BindView(R.id.suggestionphoneNumber)
    EditText suggestionPhone ;
    @BindView(R.id.suggestionsupervisorName)
    EditText suggestionSupervisorName ;
    @BindView(R.id.suggestionmedicalNameInArabic)
    EditText suggestionMedicalNameInArabic ;
    @BindView(R.id.suggestionmedicalNameInEnglish)
    EditText suggestionMedicalNameInEnglish ;
    @BindView(R.id.suggestionCounter) TextView counterSuggestionText;
    String suggestionDate=String.valueOf(Calendar.getInstance().getTime());


    SuggestionPresenterInterface presenterObject;


    private Double latitude = null;
    private Double longitude = null;

    private String description;
    private Integer medicalType;
    private String address;
    private String contactPhone;
    private String supervisor;
    private String date;
    private String medicalServiceNameAr;
    private String medicalServiceNameEn;
    Integer employeeId;
    private String phonePattern = "^\\+(?:[0-9] ?){6,14}[0-9]$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        ButterKnife.bind(this);

        enterdSuggestion.setFilters( new InputFilter[]{new InputFilter.LengthFilter(200)});
        counterSuggestionText.setText("0");
        enterdSuggestion.addTextChangedListener(mTextEditorWatcher);

        presenterObject = new SuggestionPresenter(this);



//        Intent SuggestionIntent = getIntent();
//        Bundle suggestionData = SuggestionIntent.getBundleExtra("suggestionData");
//        employeeId = suggestionData.getInt("employeeId");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        employeeId = sharedPreferences.getInt("id",0);

        String options[] = {getResources().getString(R.string.hospital), getResources().getString(R.string.clinic), getResources().getString(R.string.pharmacy), getResources().getString(R.string.lab)};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        medicalServiceTypeSpinner.setAdapter(spinnerArrayAdapter);
        medicalServiceTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medicalType = position + 1;
                Log.i("selectedPositionSpinner",position+"");
//                ( parent.getChildAt(0)).setBackgroundColor(Color.rgb(46,49,146));
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //Auto Complete fragments to get latitude and longitude of the suggested medical type
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;
                Log.i("autoComplete", "lat" + latitude);
                Log.i("autoComplete", "long" + longitude);


            }

            @Override
            public void onError(Status status) {
                Log.i("autoComplete", "Error in Autocomplete");
            }


        });

    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }



    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            counterSuggestionText.setText(String.valueOf(s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };



    @OnClick(R.id.suggestionsubmitSuggestion)
    public void submitSuggestion (){
        description = enterdSuggestion.getText().toString().trim();
        address = suggestionAddress.getText().toString().trim();
        contactPhone = suggestionPhone.getText().toString().trim();
        supervisor = suggestionSupervisorName.getText().toString().trim();
        date = suggestionDate;
        medicalServiceNameAr =  suggestionMedicalNameInArabic.getText().toString().trim();
        medicalServiceNameEn =  suggestionMedicalNameInEnglish.getText().toString().trim();



        //  if ( (employeeId !=null) || (description.isEmpty()) || (contactPhone.isEmpty()) || (date.isEmpty())
        //    ||(medicalServiceNameAr.isEmpty()) || (medicalServiceNameEn.isEmpty())
        //    || (latitude.equals(null)) || (longitude.equals(null)) || (medicalType != null)) {



        if ((description.isEmpty()) || (contactPhone.isEmpty()) || (date.isEmpty())
                || (medicalServiceNameAr.isEmpty()) || (medicalServiceNameEn.isEmpty()) || (latitude == null) || (longitude == null)) {

            Log.i("lat in if", "" + latitude);
            Log.i("long in if", "" + longitude);
            Toast.makeText(this, " please insert all required fields", Toast.LENGTH_LONG).show();
        } else {
            Log.i("lat in else", "" + latitude);
            Log.i("long in else", "" + longitude);
            Suggestion suggestion = new Suggestion();
            suggestion.setDescription(description);
            suggestion.setMedicalType(medicalType);
            suggestion.setLatitude(latitude);
            suggestion.setLongitude(longitude);

            if (!address.isEmpty()) {
                suggestion.setAddress(address);
            }
            suggestion.setContactPhone(contactPhone);

            if (!supervisor.isEmpty()) {
                suggestion.setSupervisor(supervisor);
            }
            suggestion.setDate(date);
            suggestion.setMedicalServiceNameAr(medicalServiceNameAr);
            suggestion.setMedicalServiceNameEn(medicalServiceNameEn);
            suggestion.setEmployeeId(employeeId);

            presenterObject.sendSuggestionPresenter(suggestion);
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
        Log.d("CDA", "onBackPressed Called");
        finish();
        Intent setIntent = new Intent(this, HomeActivity.class);
        startActivity(setIntent);
    }


    @Override
    public void recieveSuggestionResponseView(Boolean status) {
        if(status==true){
            //  Toast.makeText(this, "Thank you, your Suggestion has been Sent", Toast.LENGTH_LONG).show();


            AlertDialog alertDialog = new AlertDialog.Builder(SuggestionActivity.this).create();
            alertDialog.setTitle("Success");
            alertDialog.setMessage("Thank you, your Suggestion has been Sent");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent backFromReview = new Intent(SuggestionActivity.this,HomeActivity.class);
                            startActivity(backFromReview);
                        }
                    });
            alertDialog.show();


        }else {

            android.support.v7.app.AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new android.support.v7.app.AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new android.support.v7.app.AlertDialog.Builder(this);
            }
            builder.setTitle("Error")
                    .setMessage("Sorry, Something went wrong. Please try again")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}




