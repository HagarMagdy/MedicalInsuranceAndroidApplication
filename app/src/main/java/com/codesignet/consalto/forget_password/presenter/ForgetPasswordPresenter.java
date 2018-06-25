package com.codesignet.consalto.forget_password.presenter;

import android.util.Log;
import android.view.View;

import com.codesignet.consalto.forget_password.data_access_layer.ForgetPasswordInteractor;
import com.codesignet.consalto.forget_password.pojo.EmailPojo;
import com.codesignet.consalto.forget_password.ui.activity.ForgetPasswordActivity;
import com.codesignet.consalto.forget_password.view.ForgetPasswordInteractorInterface;
import com.codesignet.consalto.forget_password.view.ForgetPasswordPresenterInterface;
import com.codesignet.consalto.forget_password.view.ForgetPasswordViewInterface;

/**
 * Created by Mayada Saleh on 6/2/2018.
 */

public class ForgetPasswordPresenter implements ForgetPasswordPresenterInterface{

    private ForgetPasswordViewInterface ForgetPasswordActivity;
    private ForgetPasswordInteractorInterface interactorObject;


    public ForgetPasswordPresenter(ForgetPasswordViewInterface myView) {
        this.ForgetPasswordActivity = myView;
        initPresenter();
    }
    void initPresenter()
    {
        interactorObject=new ForgetPasswordInteractor(this);
        ForgetPasswordActivity.initView();
    }

    @Override
    public void sendEmailPresenter(EmailPojo emailPojo) {
        interactorObject.transportEmailInteractor(emailPojo);

    }

    @Override
    public void recieveResponsePresenter(Boolean status) {

        ForgetPasswordActivity.setView(status);
    }
}
