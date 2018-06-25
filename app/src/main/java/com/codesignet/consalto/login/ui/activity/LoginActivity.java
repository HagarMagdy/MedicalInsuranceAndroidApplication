package com.codesignet.consalto.login.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codesignet.consalto.R;
import com.codesignet.consalto.forget_password.ui.activity.ForgetPasswordActivity;
import com.codesignet.consalto.home.Home.activity.HomeActivity;
import com.codesignet.consalto.login.data_access_layer.retrofit_control.RetrofitClient;
import com.codesignet.consalto.login.presenter.LoginPresenter;
import com.codesignet.consalto.login.view.LoginView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.pass_edit_txt)
    EditText pass;
    @BindView(R.id.email_edit_txt)
    EditText email;
    @BindView(R.id.login_btn)
    Button login;
    @BindView(R.id.forgot_pass_txt)
    TextView forgetPassword;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        RetrofitClient.context=this;
        email.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        pass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
    }


    @OnClick(R.id.login_btn)
    public void login(View view) {
        String mail = email.getText().toString();
        String password = pass.getText().toString();
        if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(password)) {
            loginError();
        }
        else if (isValidEmail(mail) == false) {
            emailValidationFailed();
        } else {
            presenter.loginUser(mail, password, this);
        }
    }

    @Override
    public void navigateToHomeScreen(int id) {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("id",id);
        startActivity(homeIntent);
    }




    @Override
    public void loginError() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.empty), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isValidEmail(String email) {
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    public void emailValidationFailed() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.not_valid), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successLogin() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userLoginError() {
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.not_member), Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.forgot_pass_txt)
    public void forgotPassword(View view) {
        Intent forgetPasswordIntent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
        startActivity(forgetPasswordIntent);

    }
}
