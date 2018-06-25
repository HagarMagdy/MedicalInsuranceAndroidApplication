package com.codesignet.consalto.Profile.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesignet.consalto.Profile.presenter.ProfilePresenter;
import com.codesignet.consalto.Profile.retrofit_control.Pojos.Employee;
import com.codesignet.consalto.Profile.view.ProfileViewInterface;
import com.codesignet.consalto.R;
import com.codesignet.consalto.home.Home.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements ProfileViewInterface {

    @BindView(R.id.name)
    TextView Name;

    @BindView(R.id.address)
    TextView Address;

    @BindView(R.id.job)
    TextView Job;

    @BindView(R.id.start_date)
    TextView StartDate;

    @BindView(R.id.end_date)
    TextView EndDate;

    @BindView(R.id.empPackage)
    TextView Package;

    @BindView(R.id.email)
    TextView Email;

    @BindView(R.id.image)
    ImageView Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        passEmployeeID();
    }

    public void passEmployeeID() {

        ProfilePresenter presenter = new ProfilePresenter(this, ProfileActivity.this);
//        Intent intent = getIntent();
//        int id = intent.getIntExtra("id", 0);
//        Log.i("myID", "employeeID " + id);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int id = sharedPreferences.getInt("id",0);
        presenter.passEmployeeID(id);

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

    public void getEmployee(Employee employee) {

        Name.setText(employee.getName());
        Address.setText(employee.getAddress());
        Job.setText(employee.getJob());
        Email.setText(employee.getEmail());
        StartDate.setText(employee.getStartDate());
        EndDate.setText(employee.getEndDate());
        Package.setText(String.valueOf(employee.getPackageType()));
        Glide.with(this).load(employee.getImage()).into(Image);

        //Picasso.get()
        //   .load(employee.getImage())
        // .into(Image);

    }
}
