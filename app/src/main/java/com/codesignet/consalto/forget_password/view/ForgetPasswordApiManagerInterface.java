package com.codesignet.consalto.forget_password.view;

import com.codesignet.consalto.forget_password.pojo.EmailPojo;

/**
 * Created by Mayada Saleh on 6/2/2018.
 */

public interface ForgetPasswordApiManagerInterface {
    void receiveEmail(EmailPojo emailPojo);
}
