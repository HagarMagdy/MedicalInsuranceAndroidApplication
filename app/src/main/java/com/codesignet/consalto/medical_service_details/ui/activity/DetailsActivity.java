package com.codesignet.consalto.medical_service_details.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.codesignet.consalto.R;
import com.codesignet.consalto.feedback.ui.activity.FeedbackActivity;
import com.codesignet.consalto.home.Home.Pojos.ClinicPojo;
import com.codesignet.consalto.home.Home.Pojos.HospitalPojo;
import com.codesignet.consalto.home.Home.Pojos.LabPojo;
import com.codesignet.consalto.home.Home.Pojos.PharmacyPojo;
import com.codesignet.consalto.home.Home.activity.HomeActivity;
import com.codesignet.consalto.medical_service_details.presenter.DetailsPresenter;
import com.codesignet.consalto.medical_service_details.view.DetailsPresenterInterface;
import com.codesignet.consalto.medical_service_details.view.DetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements DetailsView {

    @BindView(R.id.serviceName)
    TextView serviceName;
    @BindView(R.id.startDate)
    TextView startDate;
    @BindView(R.id.endDate)
    TextView endDate;
    @BindView(R.id.open_hour)
    TextView openHour;
    @BindView(R.id.close_hour)
    TextView closeHour;
    @BindView(R.id.rate)
    TextView rate;
    @BindView(R.id.first_phone)
    TextView firstPhone;
    @BindView(R.id.second_phone)
    TextView secondPhone;
    @BindView(R.id.third_phone)
    TextView thirdPhone;
    @BindView(R.id.departments)
    TextView department;
    @BindView(R.id.sections)
    TextView sections;
    @BindView(R.id.call_first)
    ImageView callFirst;
    @BindView(R.id.call_second)
    ImageView callSecond;
    @BindView(R.id.call_third)
    ImageView callThird;
    @BindView(R.id.medical_service_pic)
    ImageView serviceImage;

    int type = 0;
    HospitalPojo hospital;
    PharmacyPojo pharmacy;
    ClinicPojo clinic;
    LabPojo lab;

    @OnClick(R.id.imageView7)
    public void goToFeedback() {
        Intent reviewIntent = new Intent(this, FeedbackActivity.class);
        Bundle reviewData = new Bundle();
        if (type == 1) {
            reviewData.putInt("medicalTypeId", hospital.getMedicalTypeId());
            reviewData.putInt("serviceId", hospital.getId());

        }
        if (type == 2) {

            reviewData.putInt("medicalTypeId", pharmacy.getMedicalTypeId());
            reviewData.putInt("serviceId", pharmacy.getId());
        }
        if (type == 3) {
            reviewData.putInt("medicalTypeId", clinic.getMedicalTypeId());
            reviewData.putInt("serviceId", clinic.getId());

        }
        if (type == 4) {
            reviewData.putInt("medicalTypeId", lab.getMedicalTypeId());
            reviewData.putInt("serviceId", lab.getId());
        }
        reviewData.putInt("employeeId", 1);

        reviewIntent.putExtra("reviewData", reviewData);
        startActivity(reviewIntent);
    }

    DetailsPresenterInterface presenter;
    static final Integer CALL = 0x2;
    public String phoneNumber = null;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        presenter = new DetailsPresenter(this);
        context = this;
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        Log.i("www", "" + type);
        if (type == 1) {
            hospital = (HospitalPojo) getIntent().getSerializableExtra("details");
            setDetailsFields(hospital);

        }
        if (type == 2) {
            pharmacy = (PharmacyPojo) getIntent().getSerializableExtra("details");
            setDetailsFields(pharmacy);

        }
        if (type == 3) {
            clinic = (ClinicPojo) getIntent().getSerializableExtra("details");
            setDetailsFields(clinic);

        }
        if (type == 4) {
            lab = (LabPojo) getIntent().getSerializableExtra("details");
            setDetailsFields(lab);
        }
//        Bundle extras = getIntent().getExtras();
//        String stringTid = extras.getString("tid");
//        String stringSid = extras.getString("sid");
//        int tid = Integer.parseInt(stringTid);
//        int sid = Integer.parseInt(stringSid);
//        presenter.getDetails(4, 2);
    }

    @Override
    public void setDetailsFields(HospitalPojo details) {
        //  name.setText("Hospita Name");

        Glide.with(this).load(details.getImage()).into(serviceImage);
        sections.setText("Departments");
        serviceName.setText(details.getNameEn());
        startDate.setText(details.getStartDate());
        endDate.setText(details.getEndDate());
        openHour.setText(details.getOpenHour());
        closeHour.setText(details.getCloseHour());
        String theRate = String.valueOf(details.getRate());

        rate.setText(theRate);

        if (details.getPhones().size() == 1) {
            firstPhone.setText(details.getPhones().get(0));
            secondPhone.setText("");
            thirdPhone.setText("");
        } else if (details.getPhones().size() == 2) {
            firstPhone.setText(details.getPhones().get(0));
            secondPhone.setText(details.getPhones().get(1));
            thirdPhone.setText("");
        } else if (details.getPhones().size() == 3) {
            firstPhone.setText(details.getPhones().get(0));
            secondPhone.setText(details.getPhones().get(1));
            thirdPhone.setText(details.getPhones().get(2));
        }
        for (int i = 0; i < details.getDepartments().size(); i++) {
            sections.append(details.getDepartments().get(i));
            sections.append("\n");
        }


    }

    @Override
    public void setDetailsFields(LabPojo details) {
        //name.setText("Lab name");
        Glide.with(this).load(details.getImage()).into(serviceImage);

        sections.setText("Specializations");
        serviceName.setText(details.getNameEn());
        startDate.setText(details.getStartDate());
        endDate.setText(details.getEndDate());
        openHour.setText(details.getOpenHour());
        closeHour.setText(details.getCloseHour());
        String theRate = String.valueOf(details.getRate());

        rate.setText(theRate);

        if (details.getLabPhones().size() == 1) {
            firstPhone.setText(details.getLabPhones().get(0));
            secondPhone.setText("");
            thirdPhone.setText("");
        } else if (details.getLabPhones().size() == 2) {
            firstPhone.setText(details.getLabPhones().get(0));
            secondPhone.setText(details.getLabPhones().get(1));
            thirdPhone.setText("");
        } else if (details.getLabPhones().size() == 3) {
            firstPhone.setText(details.getLabPhones().get(0));
            secondPhone.setText(details.getLabPhones().get(1));
            thirdPhone.setText(details.getLabPhones().get(2));
        }
        for (int i = 0; i < details.getLabSpecializations().size(); i++) {
            sections.append(details.getLabSpecializations().get(i));
            sections.append("\n");
        }
    }

    @Override
    public void setDetailsFields(ClinicPojo details) {
        //name.setText("Dr Name");
        Glide.with(this).load(details.getImage()).into(serviceImage);

        sections.setText("Specializations");
        sections.setVisibility(View.GONE);
        department.setVisibility(View.GONE);
        serviceName.setText(details.getDoctorNameEn());
        startDate.setText(details.getStartDate());
        endDate.setText(details.getEndDate());
        openHour.setText(details.getOpenHour());
        closeHour.setText(details.getCloseHour());
        String theRate = String.valueOf(details.getRate());

        rate.setText(theRate);

        if (details.getPhones().size() == 1) {
            firstPhone.setText(details.getPhones().get(0));
            secondPhone.setText("");
            thirdPhone.setText("");
        } else if (details.getPhones().size() == 2) {
            firstPhone.setText(details.getPhones().get(0));
            secondPhone.setText(details.getPhones().get(1));
            thirdPhone.setText("");
        } else if (details.getPhones().size() == 3) {
            firstPhone.setText(details.getPhones().get(0));
            secondPhone.setText(details.getPhones().get(1));
            thirdPhone.setText(details.getPhones().get(2));
        }

    }

    @Override
    public void setDetailsFields(PharmacyPojo details) {
        Glide.with(this).load(details.getImage()).into(serviceImage);

        sections.setVisibility(View.GONE);
        department.setVisibility(View.GONE);
        serviceName.setText(details.getNameEn());
        startDate.setText(details.getStartDate());
        endDate.setText(details.getEndDate());
        openHour.setText(details.getOpenHour());
        closeHour.setText(details.getCloseHour());
        String theRate = String.valueOf(details.getRate());

        rate.setText(theRate);

        if (details.getPharmacyPhones().size() == 1) {
            firstPhone.setText(details.getPharmacyPhones().get(0));
            secondPhone.setVisibility(View.GONE);
            thirdPhone.setVisibility(View.GONE);
            callSecond.setVisibility(View.GONE);
            callThird.setVisibility(View.GONE);
        } else if (details.getPharmacyPhones().size() == 2) {
            firstPhone.setText(details.getPharmacyPhones().get(0));
            secondPhone.setText(details.getPharmacyPhones().get(1));
            thirdPhone.setVisibility(View.GONE);
            callThird.setVisibility(View.GONE);

        } else if (details.getPharmacyPhones().size() == 3) {
            firstPhone.setText(details.getPharmacyPhones().get(0));
            secondPhone.setText(details.getPharmacyPhones().get(1));
            thirdPhone.setText(details.getPharmacyPhones().get(2));
        }
    }


    @Override
    public void viewError() {
        Toast.makeText(this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchActivity() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
        Toast.makeText(this, "" + getResources().getString(R.string.granted), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.call_first)
    public void callFirstOne(View view) {
        switch (view.getId()) {
            case R.id.call_first:
                phoneNumber = firstPhone.getText().toString();
                presenter.askForPermission(Manifest.permission.CALL_PHONE, CALL, context);
                break;
        }
    }

    @OnClick(R.id.call_second)
    public void callSecondOne(View view) {
        switch (view.getId()) {
            case R.id.call_second:
                phoneNumber = secondPhone.getText().toString();
                presenter.askForPermission(Manifest.permission.CALL_PHONE, CALL, context);
                break;
        }
    }


    @OnClick(R.id.call_third)
    public void callThirdOne(View view) {
        switch (view.getId()) {
            case R.id.call_third:
                phoneNumber = thirdPhone.getText().toString();
                presenter.askForPermission(Manifest.permission.CALL_PHONE, CALL, context);
                break;
        }
    }

}
