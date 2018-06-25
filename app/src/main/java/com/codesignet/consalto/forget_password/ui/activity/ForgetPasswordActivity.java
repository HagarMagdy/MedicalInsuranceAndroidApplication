package com.codesignet.consalto.forget_password.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codesignet.consalto.R;
import com.codesignet.consalto.forget_password.pojo.EmailPojo;
import com.codesignet.consalto.forget_password.presenter.ForgetPasswordPresenter;
import com.codesignet.consalto.forget_password.view.ForgetPasswordPresenterInterface;
import com.codesignet.consalto.forget_password.view.ForgetPasswordViewInterface;
import com.codesignet.consalto.home.Home.activity.HomeActivity;
import com.codesignet.consalto.login.ui.activity.LoginActivity;
import com.codesignet.consalto.suggestion.ui.activity.SuggestionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends Activity implements ForgetPasswordViewInterface{
    @BindView(R.id.forgetpasswordenteredEmail)
    EditText enteredEmail;
    @BindView(R.id.forgetpasswordsendMailBtn)
    Button sendMailButton;


    ForgetPasswordPresenterInterface pres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        // enteredEmail.setCompoundDrawables(getResources().getDrawable(R.drawable.icperson), null,null , null);
        pres=new ForgetPasswordPresenter(this);
    }


    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.forgetpasswordsendMailBtn)
    public void onButtonClicked(View view) {
        {
            Boolean state = checkInternetConnectivity();
            if (state == true) {

                if (enteredEmail.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    EmailPojo emailPojo = new EmailPojo();
                    emailPojo.setMail(enteredEmail.getText().toString().trim());
                    pres.sendEmailPresenter(emailPojo);

                } else {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.mailValidation), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.Internt_access), Toast.LENGTH_LONG).show();
            }
        }
    }
    //Check Internet Connectivity
    public boolean checkInternetConnectivity() {
        boolean status;
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    @Override
    public void setView(Boolean data) {
        if (data == true){
            Log.i("key","in true method");
            AlertDialog alertDialog = new AlertDialog.Builder(ForgetPasswordActivity.this).create();
            alertDialog.setTitle("Success");
            alertDialog.setMessage("Thank you, please check your mail");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent loginIntent = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                            startActivity(loginIntent);
                        }
                    });
            alertDialog.show();


        }
        else {

            android.support.v7.app.AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new android.support.v7.app.AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new android.support.v7.app.AlertDialog.Builder(this);
            }
            builder.setTitle("Error")
                    .setMessage(getApplicationContext().getResources().getString(R.string.mailCheckDatabase))
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
