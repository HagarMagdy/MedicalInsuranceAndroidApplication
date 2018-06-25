package com.codesignet.consalto.forget_password.data_access_layer;

import com.codesignet.consalto.forget_password.pojo.EmailPojo;
import com.codesignet.consalto.forget_password.view.ForgetPasswordApiManagerInterface;
import com.codesignet.consalto.forget_password.view.ForgetPasswordInteractorInterface;
import com.codesignet.consalto.forget_password.view.ForgetPasswordPresenterInterface;

/**
 * Created by Mayada Saleh on 6/2/2018.
 */

public class ForgetPasswordInteractor implements ForgetPasswordInteractorInterface {
    private ForgetPasswordPresenterInterface myPresenter;
    private ForgetPasswordApiManagerInterface myApi;

    public ForgetPasswordInteractor(ForgetPasswordPresenterInterface presenter) {
        this.myPresenter = presenter;
        initInteractor();
    }


    @Override
    public void initInteractor() {
        myApi=new ForgetPasswordApiManager(this);
    }

    @Override
    public void transportEmailInteractor(EmailPojo emailPojo) {
        myApi.receiveEmail(emailPojo);
    }

    @Override
    public void recieveResponseInteractor(Boolean status) {
        myPresenter.recieveResponsePresenter(status);
    }
}
