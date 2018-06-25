package com.codesignet.consalto.forget_password.view;

import com.codesignet.consalto.forget_password.pojo.EmailPojo;

/**
 * Created by Mayada Saleh on 6/2/2018.
 */

public interface ForgetPasswordInteractorInterface {

    void initInteractor();
    void transportEmailInteractor(EmailPojo emailPojo);
    void recieveResponseInteractor(Boolean status);
}
