package com.codesignet.consalto.Settings.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.codesignet.consalto.R;
import com.codesignet.consalto.Settings.presenter.SettingsPresenter;
import com.codesignet.consalto.Settings.view.SettingsViewInterface;
import com.codesignet.consalto.home.Home.activity.HomeActivity;
import com.codesignet.consalto.login.ui.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity implements SettingsViewInterface {

    private SettingsViewInterface View;
    @BindView(R.id.hospital)
    CheckBox Hospital;

    @BindView(R.id.pharmacy)
    CheckBox Pharmacy;

    @BindView(R.id.clinic)
    CheckBox Clinic;

    @BindView(R.id.lab)
    CheckBox Lab;

    @BindView(R.id.save)
    Button Save;

    @BindView(R.id.logout)
    Button Logout;

    @BindView(R.id.english)
    RadioButton English;

    @BindView(R.id.arabic)
    RadioButton Arabic;

    boolean[] myChoices = new boolean[4];
    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        Toast.makeText(this, "in on create", Toast.LENGTH_LONG).show();
        SettingsPresenter presenter = new SettingsPresenter(this, SettingsActivity.this);
        getChoices();
        getLocale();
        presenter.setLocale(language, getBaseContext());

        Log.i("myTag", "languageeeeeeeeeeeeeeee" + language);

        if (language.equals("en"))
            English.setChecked(true);
        if (language.equals("ar"))
            Arabic.setChecked(true);

        if (myChoices[0]) {
            Hospital.setChecked(true);
            Log.i("myTag", "load hospital " + myChoices[0]);
        } else {
            Hospital.setChecked(false);
            Log.i("myTag", "load pharmacy" + myChoices[0]);
        }

        if (myChoices[1]) {
            Pharmacy.setChecked(true);
            Log.i("myTag", "load pharmacy " + myChoices[1]);
        } else {
            Pharmacy.setChecked(false);
            Log.i("myTag", "load pharmacy " + myChoices[1]);
        }

        if (myChoices[2]) {
            Lab.setChecked(true);
            Log.i("myTag", "load lab " + myChoices[2]);
        } else {
            Lab.setChecked(false);
            Log.i("myTag", "load lab " + myChoices[2]);
        }

        if (myChoices[3]) {
            Clinic.setChecked(true);
            Log.i("myTag", "load clinic " + myChoices[3]);

        } else {
            Clinic.setChecked(false);
            Log.i("myTag", "load clinic " + myChoices[3]);

        }
    }

    @OnClick(R.id.save)
    public void save(android.view.View view) {

        if (Arabic.isChecked()) {
            language = "ar";
        }

        if (English.isChecked()) {
            language = "en";
        }
        SettingsPresenter presenter = new SettingsPresenter(this, SettingsActivity.this);
        boolean[] choices = new boolean[4];
        choices[0] = Hospital.isChecked();
        choices[1] = Pharmacy.isChecked();
        choices[2] = Lab.isChecked();
        choices[3] = Clinic.isChecked();

        presenter.saveMedicalTypesChoices(choices);
        presenter.setLocale(language, getBaseContext());
        Intent intent = new Intent(SettingsActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.logout)
    public void logout(View view)
    {
        SettingsPresenter presenter= new SettingsPresenter(this, this);
        presenter.logout(this);

        SharedPreferences prefs = getSharedPreferences("Settings", SettingsActivity.MODE_PRIVATE);
        String language = prefs.getString("Language", "");
        Log.i("myTag","language"+language);
        Intent i= new Intent(this, LoginActivity.class);
        startActivity(i);

    }

    public void getChoices() {
        SettingsPresenter presenter = new SettingsPresenter(this, SettingsActivity.this);
        presenter.getChoices();
    }

    public void getChoices(boolean[] choices) {
        this.myChoices = choices;
    }

    public void getLocale() {
        SettingsPresenter presenter = new SettingsPresenter(this, SettingsActivity.this);
        presenter.getLocale();
    }

    public void getLocaleFromShared(String language) {
        this.language = language;
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
}