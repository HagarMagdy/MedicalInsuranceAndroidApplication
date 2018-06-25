package com.codesignet.consalto.contactus.presenter;

import android.content.Context;

import com.codesignet.consalto.contactus.data_access_layer.ContactUsInteractor;
import com.codesignet.consalto.contactus.pojo.ContactUs;
import com.codesignet.consalto.contactus.view.ContactUsInteractorInterface;
import com.codesignet.consalto.contactus.view.ContactUsPresenterInterface;
import com.codesignet.consalto.contactus.view.ContactUsViewInterface;

/**
 * Created by Aya on 02/06/2018.
 */

public class ContactUsPresenter implements ContactUsPresenterInterface{
    ContactUsViewInterface activityObject;
    ContactUsInteractorInterface interactorObject;
    Context context;

    public ContactUsPresenter(ContactUsViewInterface activityObject,Context context) {
        this.activityObject = activityObject;
        this.interactorObject=new ContactUsInteractor(this,context);
        this.context=context;
    }

    @Override
    public void sendMailPresenter(ContactUs contactUsObject) {
        interactorObject.sendMailInteractor(contactUsObject);
    }
}
