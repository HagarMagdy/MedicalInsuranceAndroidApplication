package com.codesignet.consalto.feedback.ui.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codesignet.consalto.R;
import com.codesignet.consalto.feedback.pojo.Review;
import com.codesignet.consalto.feedback.presenter.FeedbackPresenter;
import com.codesignet.consalto.feedback.view.FeedbackPresenterInterface;
import com.codesignet.consalto.feedback.view.FeedbackViewInterface;
import com.codesignet.consalto.home.Home.activity.HomeActivity;
import com.codesignet.consalto.login.data_access_layer.retrofit_control.SharedPreference;
import com.codesignet.consalto.suggestion.ui.activity.SuggestionActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FeedbackActivity extends AppCompatActivity implements FeedbackViewInterface,AdapterView.OnItemSelectedListener{
//@BindView(R.id.textDate) TextView dateText;
    @BindView(R.id.MedicalTypeSpinner)
    Spinner MedicalTypeSpinner;
    @BindView(R.id.descriptionText)
    EditText descriptionText ;
    @BindView(R.id.ratingbar) com.iarcuschin.simpleratingbar.SimpleRatingBar ratingBarValue;
    @BindView (R.id.dateBtn)
    Button dateBtnReview;
    @BindView(R.id.textViewRate)TextView textRate;
    @BindView(R.id.counterTxt) TextView counterText;
    boolean keyFlag = true;
    int feedbackType=-1;
    int reviewId=0;
    boolean rateFlagFirstTime=false;
    FeedbackPresenterInterface presenterObject;
    String medicalTypeValue;
    int employeeId,medicalTypeId,serviceId;
    int mYear2,mMonth2,mDay2;
    String reviewDate=String.valueOf(Calendar.getInstance().getTime());
    DatePickerDialog datePicker;
    @OnClick(R.id.submitReview)
    public void submitReview (){
        if (feedbackType==0) {
            String descriptionValue = descriptionText.getText().toString();
            if (descriptionValue.isEmpty()) {
                Toast.makeText(this, " please insert at least all fields", Toast.LENGTH_LONG).show();
            } else {
                Review review = new Review();
                review.setEmployeeEmployeeId(employeeId);
                review.setMedicalTypeId(medicalTypeId);
                review.setServiceId(serviceId);
                review.setDescription(descriptionValue);
                review.setReviewRate(0);
                review.setType(medicalTypeValue);
                review.setDate(reviewDate);
                Log.i("keyFLag"," keyFlag in activity"+ serviceId);
                presenterObject.sendReview(review,rateFlagFirstTime);
            }
        } else if (feedbackType==1) {
            float rateValue = ratingBarValue.getRating();
            if(rateValue==0.0) {
                Toast.makeText(this, " please insert at least one of these fields", Toast.LENGTH_LONG).show();
            }
            else{
                Review review = new Review();
                review.setEmployeeEmployeeId(employeeId);
                review.setMedicalTypeId(medicalTypeId);
                review.setServiceId(serviceId);
                review.setDescription(null);
                review.setReviewRate(rateValue);
                review.setType(medicalTypeValue);
                review.setDate(reviewDate);
                rateFlagFirstTime=true;
                presenterObject.sendReview(review,rateFlagFirstTime);
            }


        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        presenterObject = new FeedbackPresenter(this,getApplication().getApplicationContext());
        Intent reviewIntent = getIntent();
        Bundle reviewData = reviewIntent.getBundleExtra("reviewData");
        medicalTypeId = reviewData.getInt("medicalTypeId");
        serviceId = reviewData.getInt("serviceId");
        SharedPreferences sharedPrefrence = PreferenceManager.getDefaultSharedPreferences(this);
        employeeId = sharedPrefrence.getInt("id",0);
        ButterKnife.bind(this);
        callPresenter();
        Log.i("keyFLag"," keyFlag in on create"+ reviewId+"main data is"+serviceId);
        if(keyFlag==false || serviceId != reviewId) {
            String options[] = {getResources().getString(R.string.spinnerHint),getResources().getString(R.string.positive),getResources().getString(R.string.negative),getResources().getString(R.string.rate)};
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options){ @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position == 0){
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            MedicalTypeSpinner.setAdapter(spinnerArrayAdapter);
       }
       else{
            String options[] = {getResources().getString(R.string.spinnerHint),getResources().getString(R.string.positive),getResources().getString(R.string.negative) };
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options) {
                @Override
                public boolean isEnabled(int position) {
                    if (position == 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position == 0){
                        tv.setTextColor(Color.GRAY);
                    }
                    else {
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            MedicalTypeSpinner.setAdapter(spinnerArrayAdapter);
       }
        MedicalTypeSpinner.setOnItemSelectedListener(this);

    }



    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            counterText.setText(String.valueOf(s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };




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
        finish();
        Intent setIntent = new Intent(this, HomeActivity.class);
        startActivity(setIntent);
    }
    @Override
    public void onStart() {
        super.onStart();
        dateBtnReview.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear2 = c.get(Calendar.YEAR);
                mMonth2 = c.get(Calendar.MONTH);
                mDay2 = c.get(Calendar.DAY_OF_MONTH);

                datePicker = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                               dateBtnReview.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                             //   reviewDate = dateText.getText().toString();
                            }
                        }, mYear2, mMonth2, mDay2);
                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePicker.show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 1:
                medicalTypeValue="review";
                descriptionText.setVisibility(View.VISIBLE);
                ratingBarValue.setVisibility(View.INVISIBLE);
                counterText.setVisibility(View.VISIBLE);
                textRate.setVisibility(View.GONE);
                descriptionText.setText("");
                counterText.setText("0");
                descriptionText.addTextChangedListener(mTextEditorWatcher);
                feedbackType=0;
                break;
            case 2:
                medicalTypeValue="complain";
                descriptionText.setVisibility(View.VISIBLE);
                ratingBarValue.setVisibility(View.INVISIBLE);
                counterText.setVisibility(View.VISIBLE);
                textRate.setVisibility(View.GONE);
                descriptionText.setText(null);
                counterText.setText("0");
                descriptionText.addTextChangedListener(mTextEditorWatcher);
                feedbackType=0;
                break;

            case 3:
                medicalTypeValue="rate";
                ratingBarValue.setVisibility(View.VISIBLE);
                ratingBarValue.setRating(0);
                ratingBarValue.setPressed(false);
                descriptionText.setVisibility(View.INVISIBLE);
                textRate.setVisibility(View.VISIBLE);
                counterText.setVisibility(View.INVISIBLE);
                feedbackType=1;
                rateFlagFirstTime=true;
                break;
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        medicalTypeValue="positive";
        feedbackType=0;
        Toast.makeText(this,medicalTypeValue,Toast.LENGTH_LONG).show();
    }

    @Override
    public void takeAction(int returnResult) {
        if(returnResult==0)
        {
            android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(FeedbackActivity.this).create();
            alertDialog.setTitle("Success");
            alertDialog.setMessage("Thank you, your Feedback has been Sent");
            alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent backFromReview = new Intent(FeedbackActivity.this,HomeActivity.class);
                            startActivity(backFromReview);
                        }
                    });
            alertDialog.show();
        }
        else
        {
            final AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Fail")
                    .setMessage("sorry,there is a problem in sending your Feedback")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent backFromReview = new Intent(FeedbackActivity.this,HomeActivity.class);
                            startActivity(backFromReview);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }

    @Override
    public void returnRateFlag(boolean rateFlag) {

    }

    @Override
    public void existKeyActivity(boolean keyFlag,int reviewId) {
       this.keyFlag=keyFlag;
       this.reviewId=reviewId;
    }

    @Override
    public void callPresenter() {
        presenterObject.callInteractor();
    }
}
